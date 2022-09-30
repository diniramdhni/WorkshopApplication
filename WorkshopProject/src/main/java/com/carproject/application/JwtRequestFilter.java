package com.carproject.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtToken jwtToken;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //dapatkan header dri request ditampung di autorizationheader

        System.out.println("inside dofilterinternal");
        String authorizationHeader = request.getHeader("Authorization");
        System.out.println("inside authorizationheader: " + authorizationHeader);
        String username = null;
        String token = null;

        if(authorizationHeader != null){
            System.out.println("inside if authorizationheader");
            token = authorizationHeader.replace("Bearer", "");
            //get username from JWT token
            username = jwtToken.getUsername(token);
//            System.out.println("role = " + jwtToken.g);

        }

        //container
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication: " + authentication);
        if(username != null){
            System.out.println("username:" + username);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(jwtToken.validationToken(token, userDetails)){ //memvalidasi token
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()

                );
                System.out.println("userdetail getauthoriti: " + userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);

    }


}
