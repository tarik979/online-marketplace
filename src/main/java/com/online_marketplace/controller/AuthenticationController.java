package com.online_marketplace.controller;


import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online_marketplace.exception.UserAlreadyExistsException;
import com.online_marketplace.repository.RoleRepository;
import com.online_marketplace.repository.UserRespository;
import com.online_marketplace.request.LoginRequestBody;
import com.online_marketplace.request.RegisteRequestBody;
import com.online_marketplace.response.MessageResponse;
import com.online_marketplace.response.UserInfoResponse;
import com.online_marketplace.security.jwt.JwtUtils;
import com.online_marketplace.service.UserDetailsImpl;
import com.online_marketplace.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;



@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    
     @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRespository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestBody loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        jwtUtils.getCleanJwtCookie();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        return ResponseEntity.ok().body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        userDetails.getCreatedAt(),

                        roles, jwtCookie.toString()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody RegisteRequestBody signUpRequest) throws UserAlreadyExistsException {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        
        userService.resgisterUSer(signUpRequest);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }
}
