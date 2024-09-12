package com.online_marketplace.controller;

import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online_marketplace.constants.ApplicationConstants;
import com.online_marketplace.exception.UserAlreadyExistsException;
import com.online_marketplace.request.LoginRequestBody;
import com.online_marketplace.request.RegisteRequestBody;
import com.online_marketplace.response.LoginResponseDTO;
import com.online_marketplace.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;



@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final Environment env;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisteRequestBody registeRequestBody){
        try {
            userService.resgisterUSer(registeRequestBody);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> apiLogin (@RequestBody LoginRequestBody loginRequest) {
        String jwt = "";
        Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getEmail(),
                loginRequest.getPassword());
        Authentication authenticationResponse = authenticationManager.authenticate(authentication);
        if(null != authenticationResponse && authenticationResponse.isAuthenticated()) {
            if (null != env) {
                String secret = env.getProperty(ApplicationConstants.JWT_SECRET_KEY,
                        ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);
                SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
                 jwt = Jwts.builder().issuer("ISSUAR").subject("JWT Token")
                        .claim("username", authenticationResponse.getName())
                        .claim("authorities", authenticationResponse.getAuthorities().stream().map(
                                GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                        .issuedAt(new java.util.Date())
                        .expiration(new java.util.Date((new java.util.Date()).getTime() + 30000000))
                        .signWith(secretKey).compact();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).header(ApplicationConstants.JWT_HEADER,jwt)
                .body(new LoginResponseDTO(HttpStatus.OK.getReasonPhrase(), jwt));
}
}
