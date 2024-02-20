package com.aftas.aftasbackend.service.implementations;

import com.aftas.aftasbackend.config.JwtService;
import com.aftas.aftasbackend.enums.RoleName;
import com.aftas.aftasbackend.enums.TokenType;
import com.aftas.aftasbackend.model.dto.request.AuthenticationRequest;
import com.aftas.aftasbackend.model.dto.request.RefreshTokenRequest;
import com.aftas.aftasbackend.model.dto.request.RegisterRequest;
import com.aftas.aftasbackend.model.dto.response.AuthenticationResponse;
import com.aftas.aftasbackend.model.dto.response.RefreshTokenResponse;
import com.aftas.aftasbackend.model.entities.Member;
import com.aftas.aftasbackend.model.entities.Role;
import com.aftas.aftasbackend.model.entities.Token;
import com.aftas.aftasbackend.repository.RoleRepository;
import com.aftas.aftasbackend.repository.TokenRepository;
import com.aftas.aftasbackend.repository.UserRepository;
import com.aftas.aftasbackend.service.AuthenticationService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final MemberServiceImpl memberService;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        Role userRole = roleRepository.findByName(RoleName.MEMBER)
                .orElseThrow(() -> new EntityNotFoundException("Role not found in the database"));
        Member member = memberService.createMemberRegister(request);
        var user = Member.builder()
                .name(request.getName())
                .familyName(request.getFamilyName())
                .identityNumber(member.getIdentityNumber())
                .accessionDate(LocalDate.now())
                .identityDocument(member.getIdentityDocument())
                .nationality(member.getNationality())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(userRole)
                .enabled(false)
                .build();
        userRepository.save(user);
        return authenticationResponse(user);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        Member user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return authenticationResponse(user);
    }

    private void saveUserToken(Member user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(Member user) {
        var validUserTokens = tokenRepository.findAllValidTokensByUserId(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest, HttpServletResponse response) throws IOException {
        String refreshToken = refreshTokenRequest.getRefreshToken();

        if (refreshToken != null) {
            String userEmail = jwtService.extractUserName(refreshToken);

            if (userEmail != null) {
                Member user = userRepository.findByEmail(userEmail)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

                if (jwtService.isTokenValid(refreshToken, user)) {
                    var accessToken = jwtService.generateToken(user);
                    revokeAllUserTokens(user);
                    saveUserToken(user, accessToken);
                    return RefreshTokenResponse.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshToken)
                            .build();
                }
            }
        }
        throw new AuthenticationException("Refresh token validation failed");
    }



    private AuthenticationResponse authenticationResponse(Member user){
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .tokenType(TokenType.BEARER.name())
                .id(user.getId())
                .email(user.getEmail())
                .roles(user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .enabled(user.isEnabled())
                .build();
    }

}
