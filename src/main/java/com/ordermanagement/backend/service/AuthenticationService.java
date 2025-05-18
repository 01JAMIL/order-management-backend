package com.ordermanagement.backend.service;


import com.ordermanagement.backend.common.AuthenticationResponse;
import com.ordermanagement.backend.common.LoginPayload;
import com.ordermanagement.backend.common.RegisterPayload;
import com.ordermanagement.backend.common.UserRole;
import com.ordermanagement.backend.model.User;
import com.ordermanagement.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterPayload payload) {
        var user = User.builder()
                .firstName(payload.firstName())
                .lastName(payload.lastName())
                .email(payload.email())
                .password(passwordEncoder.encode(payload.password()))
                .role(UserRole.ADMIN)
                .build();

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .user(user)
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse login(LoginPayload payload) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        payload.email(),
                        payload.password()
                )
        );
        var user = userRepository.findByEmail(payload.email())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .user(user)
                .token(jwtToken)
                .build();
    }
}
