package com.online_marketplace.security;

// import java.io.IOException;
// import java.util.Optional;

// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import com.auth0.jwt.exceptions.JWTDecodeException;
// import com.online_marketplace.model.LocalUser;
// import com.online_marketplace.repository.UserRespository;
// import com.online_marketplace.service.JWTService;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// @Component
public class JWTRequestFilter /* extends */{
    // private final JWTService jwtService;
    // private final UserRespository userRespository;

    // public JWTRequestFilter(JWTService jwtService, UserRespository userRespository){
    //     this.jwtService = jwtService;
    //     this.userRespository = userRespository;
    // }

    
    // @SuppressWarnings("null")
    // @Override
    // protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    //         throws ServletException, IOException {
    //         String tokenHeader = request.getHeader("Authorization");
    //         if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
    //             String token = tokenHeader.substring(7);
    //            try{
    //             String email = jwtService.getEmail(token);
    //             Optional<LocalUser> opUSer = userRespository.findByEmailIgnoreCase(email);
    //             if (opUSer.isPresent()) {
    //                 LocalUser user = opUSer.get();
    //                 UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
    //                 authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    //                 SecurityContextHolder.getContext().setAuthentication(authentication);
    //             }
    //            }catch(JWTDecodeException e){

    //            }

    //         }

    //         filterChain.doFilter(request, response);
    // }
    
}
