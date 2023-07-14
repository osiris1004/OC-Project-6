package com.openclassrooms.mddapi.Auth.service;




import com.openclassrooms.mddapi.Auth.controller.authenticate.AuthRequest;
import com.openclassrooms.mddapi.Auth.controller.authenticate.AuthResponse;
import com.openclassrooms.mddapi.Auth.controller.registry.RegisterRequest;
import com.openclassrooms.mddapi.config.Security.middleware.JwtService;
import com.openclassrooms.mddapi.modles.User.Role;
import com.openclassrooms.mddapi.modles.User.User;
import com.openclassrooms.mddapi.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

        private final UserRepository repository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        public AuthResponse register(RegisterRequest request) {
                var user = User
                                .builder()
                                .name(request.getName())
                                .email(request.getEmail())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .role(Role.USER)
                                .build();

                repository.save(user);

                var jwtToken = jwtService.generateToken(user);

                return AuthResponse
                                .builder()
                                .token(jwtToken)
                                .build();
        }

        public AuthResponse authenticate(AuthRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

                var user = repository.findByEmail(request.getEmail()).orElseThrow();

                var jwtToken = jwtService.generateToken(user);

                return AuthResponse
                                .builder()
                                .token(jwtToken)
                                .build();
        }
}
