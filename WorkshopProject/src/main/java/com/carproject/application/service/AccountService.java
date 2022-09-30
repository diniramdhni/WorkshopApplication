package com.carproject.application.service;

import com.carproject.application.dto.InsertAccountDTO;

public interface AccountService {
    String getAccountRole(String username);

    void insertAccount(InsertAccountDTO dto);

    boolean checkExistingAccount(String username);
}
