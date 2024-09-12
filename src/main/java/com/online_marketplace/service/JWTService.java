package com.online_marketplace.service;

// import java.util.Date;
// import java.util.List;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.stereotype.Service;

// import com.auth0.jwt.JWT;
// import com.auth0.jwt.algorithms.Algorithm;
// import com.online_marketplace.model.LocalUser;

// import jakarta.annotation.PostConstruct;

// @Service
public class JWTService {
    // @Value("${jwt.algorithm.key}")
    // private String algorithmkey;

    // @Value("${jwt.issuer}")
    // private String issuer;

    // @Value("${jwt.expiryInseconds}")
    // private Integer expiryInSeconds;

    // protected Algorithm algorithm;

    // private static final String EMAIL_KEY = "EMAIL";
    // private static final String ROLE_KEY = "authorities";
    
    // @PostConstruct
    // public void PostConstruct(){
    //     algorithm = Algorithm.HMAC256(algorithmkey);
    // }

    // public String generateToken(LocalUser user,Authentication authenticationResponse){
    //     return JWT.create()
    //     .withClaim(EMAIL_KEY, authenticationResponse.getName())
    //     .withClaim(ROLE_KEY, List.of(authenticationResponse.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","))))
    //     .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * expiryInSeconds)))
    //     .withIssuer(issuer)
    //     .sign(algorithm);
    // }

    // public String getEmail(String token){
    //     return JWT.decode(token).getClaim(EMAIL_KEY).asString();
    // }

    // public String getRole(String token){
    //     return JWT.decode(token).getClaim(ROLE_KEY).asString();
    // }

}
