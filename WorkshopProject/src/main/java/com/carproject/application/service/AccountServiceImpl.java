package com.carproject.application.service;

import com.carproject.application.ApplicationUserDetails;
import com.carproject.application.configuration.RestSecurityConfiguration;
import com.carproject.application.dto.InsertAccountDTO;
import com.carproject.application.entity.Account;
import com.carproject.application.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService, UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public String getAccountRole(String username) {
        Optional<Account> accountOptional = accountRepository.findById(username);
        Account temptAccount = null;
        if(accountOptional.isPresent()){
            temptAccount = accountOptional.get();
        }
        return temptAccount.getRole();
    }

    @Override
    public void insertAccount(InsertAccountDTO dto) {
        PasswordEncoder passwordEncoder = RestSecurityConfiguration.passwordEncoder();
        //encode password
        String hashPassword = passwordEncoder.encode(dto.getPassword());
        //initialize account

        Account account = new Account(
                dto.getUsername(),
                hashPassword, dto.getRole().toUpperCase()
        );

        //save akun
        accountRepository.save(account);

    }

    @Override
    public boolean checkExistingAccount(String username) {
        Long totalUser = accountRepository.count(username);
        return totalUser > 0 ? true : false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> nullableEntity = accountRepository.findById(username);
        Account account = nullableEntity.get();
        return new ApplicationUserDetails(account);
    }
}
