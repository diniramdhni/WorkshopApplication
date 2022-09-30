package com.carproject.application.controller;
import com.carproject.application.JwtToken;
import com.carproject.application.NotFoundException;
import com.carproject.application.dto.InsertAccountDTO;
import com.carproject.application.dto.RequestTokenDTO;
import com.carproject.application.dto.ResponseTokenDTO;
import com.carproject.application.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Locale;

@RestController
@RequestMapping("/api")
public class AccountRestController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public ResponseTokenDTO post(@RequestBody RequestTokenDTO dto){
        System.out.println("RequesttokenDto:" + dto );
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    dto.getUsername(),
                    dto.getPassword()
            );
            Authentication authentication = authenticationManager.authenticate(token);
            System.out.println("Is authenticate: " + authentication.isAuthenticated());
            System.out.println("Principal: " + authentication.getPrincipal());

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not authenticate", e);
        }
//        UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getUsername());
        String role =  accountService.getAccountRole(dto.getUsername());
        String token = jwtToken.generateToken(dto.getSubject(), dto.getUsername(), dto.getSecretKey(), role, dto.getAudience());
        ResponseTokenDTO responseTokenDTO = new ResponseTokenDTO(dto.getUsername(), token, role);

        return responseTokenDTO;
    }

    //daftar account
    @PostMapping("/postAccount")
    public ResponseEntity<String> postAccount(@Valid @RequestBody InsertAccountDTO dto){
        if(dto.getRole().toLowerCase().equals("admin") || dto.getRole().toLowerCase().equals("customer")) {

            accountService.insertAccount(dto);

            return new ResponseEntity<>("Congratulations, your account has been registered successfully.", HttpStatus.CREATED);
        } else{
            throw new NotFoundException("Role " + dto.getRole() + " not found in the database!");
        }
    }

}
