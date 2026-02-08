package com.example.PracticaSpringSecurity.auth;


import com.example.PracticaSpringSecurity.config.JwtService;
import com.example.PracticaSpringSecurity.user.Rol;
import com.example.PracticaSpringSecurity.user.User;
import com.example.PracticaSpringSecurity.user.UserRepository;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.PasswordAuthentication;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthentificationResponse register(RegisterRequest request) {
        var user = User.builder().
                firstName(request.getFirstName()).
                lastName(request.getLastName()).
                email(request.getEmail()).
                password(passwordEncoder.encode(request.getPassword())).
                role(Rol.USER).
                build();
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthentificationResponse.builder().token(jwtToken).build();
    }

    public AuthentificationResponse authentificate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return AuthentificationResponse.builder().token(jwtToken).build();

    }
}
