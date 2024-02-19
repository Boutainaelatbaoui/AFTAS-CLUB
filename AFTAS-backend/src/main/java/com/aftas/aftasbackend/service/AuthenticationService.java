package com.aftas.aftasbackend.service;


import com.aftas.aftasbackend.model.dto.request.AuthenticationRequest;
import com.aftas.aftasbackend.model.dto.request.RefreshTokenRequest;
import com.aftas.aftasbackend.model.dto.request.RegisterRequest;
import com.aftas.aftasbackend.model.dto.response.AuthenticationResponse;
import com.aftas.aftasbackend.model.dto.response.RefreshTokenResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
    AuthenticationResponse register(RegisterRequest request);
    RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest, HttpServletResponse response) throws IOException;
}
