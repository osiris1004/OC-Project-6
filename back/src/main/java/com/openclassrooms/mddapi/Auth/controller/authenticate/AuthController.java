package com.openclassrooms.mddapi.Auth.controller.authenticate;





import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.mddapi.controllers.resquests.UserRequest;
import com.openclassrooms.mddapi.modles.Theme;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.openclassrooms.mddapi.Auth.controller.registry.RegisterRequest;
import com.openclassrooms.mddapi.Auth.service.AuthService;
import com.openclassrooms.mddapi.config.Security.middleware.JwtService;
import com.openclassrooms.mddapi.modles.User.User;
import com.openclassrooms.mddapi.services.User.UserService;

import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor


public class AuthController {

    private final AuthService service;
    private final UserService userService;
    private final JwtService jwtService;

    ObjectMapper objectMapper = new ObjectMapper();


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {

        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        System.out.println("test");
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> themes() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new Exception("Exception message");
            }
            String jwt = authHeader.substring(7);
            String userEmail = jwtService.extractUserName(jwt);
            return ResponseEntity.ok(userService.getUserByEmail(userEmail));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/user")
    public ResponseEntity<User> update(HttpServletRequest request) throws IOException {
        UserRequest stringToObject = objectMapper.readValue(request.getReader().lines().reduce("",String::concat), UserRequest.class);

        final String authHeader = request.getHeader("Authorization");
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new Exception("Exception message");
            }
            String jwt = authHeader.substring(7);
            String userEmail = jwtService.extractUserName(jwt);

            User currentUser = userService.getUserByEmail(userEmail);
            currentUser.setName(stringToObject.getName());
            currentUser.setEmail(stringToObject.getEmail());
            return ResponseEntity.ok(userService.updateUser(currentUser));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
