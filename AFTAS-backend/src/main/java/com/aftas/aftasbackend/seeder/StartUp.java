package com.aftas.aftasbackend.seeder;

import com.aftas.aftasbackend.enums.IdentityDocumentType;
import com.aftas.aftasbackend.enums.Permission;
import com.aftas.aftasbackend.enums.RoleName;
import com.aftas.aftasbackend.model.entities.Member;
import com.aftas.aftasbackend.model.entities.Privilege;
import com.aftas.aftasbackend.model.entities.Role;
import com.aftas.aftasbackend.repository.PrivilegeRepository;
import com.aftas.aftasbackend.repository.RoleRepository;
import com.aftas.aftasbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
@Order(1)
public class StartUp implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public StartUp(UserRepository userRepository,
                   RoleRepository roleRepository,
                   PrivilegeRepository privilegeRepository,
                   PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (userRepository.count() > 0) {
            return;
        }

        Privilege readPrivilege = createPrivilegeIfNotFound(Permission.CAN_READ);
        Privilege readParPrivilege = createPrivilegeIfNotFound(Permission.CAN_READ_PARTICIPATIONS);
        Privilege readPodPrivilege = createPrivilegeIfNotFound(Permission.CAN_READ_PODIUM_INFO);
        Privilege manageComPrivilege = createPrivilegeIfNotFound(Permission.CAN_MANAGE_COMPETITIONS);
        Privilege manageUserPrivilege = createPrivilegeIfNotFound(Permission.CAN_MANAGE_USERS);

        Role adminRole = createRoleIfNotFound(RoleName.JURY, Arrays.asList(readPrivilege, readParPrivilege, readPodPrivilege, manageComPrivilege));
        Role userRole = createRoleIfNotFound(RoleName.MEMBER, Arrays.asList(readPrivilege, readParPrivilege, readPodPrivilege));
        Role superAdminRole = createRoleIfNotFound(RoleName.MANAGER, Arrays.asList(readPrivilege, readParPrivilege, readPodPrivilege, manageComPrivilege, manageUserPrivilege));

        Member adminUser = createUser(2, false, LocalDate.now(),"Jury", "User",  "MA", IdentityDocumentType.CIN, "AC12315423", "adminpassword", "admin@example.com", adminRole);
        userRepository.save(adminUser);

        Member regularUser = createUser(3, false, LocalDate.now(),"user", "user", "FR", IdentityDocumentType.CARTE_RESIDENCE, "AB12315423", "userpassword", "user@example.com", userRole);
        userRepository.save(regularUser);

        Member superAdminUser = createUser(1, false, LocalDate.now(),"Manager", "admin", "CA", IdentityDocumentType.PASSPORT, "AA12315423","superadminpassword", "super-admin@example.com", superAdminRole);
        userRepository.save(superAdminUser);
    }

    private Privilege createPrivilegeIfNotFound(Permission name) {
        return privilegeRepository.findByName(name)
                .orElseGet(() -> privilegeRepository.save(Privilege.builder().name(name).build()));
    }

    private Role createRoleIfNotFound(RoleName name, List<Privilege> privileges) {
        return roleRepository.findByName(name)
                .orElseGet(() -> roleRepository.save(Role.builder().name(name).privileges(privileges).build()));
    }

    private Member createUser(int num, boolean enabled, LocalDate accesionDate, String name, String familyName, String nationality , IdentityDocumentType identityDocument, String identityNumber , String password, String email, Role role) {
        return userRepository.save(Member.builder()
                .num(num)
                .name(name)
                .familyName(familyName)
                .nationality(nationality)
                .identityDocument(identityDocument)
                .identityNumber(identityNumber)
                .password(passwordEncoder.encode(password))
                .email(email)
                .role(role)
                .enabled(enabled)
                .accessionDate(accesionDate)
                .build());
    }
}
