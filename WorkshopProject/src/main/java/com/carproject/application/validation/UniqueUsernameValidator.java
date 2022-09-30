package com.carproject.application.validation;

import com.carproject.application.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String>{
	
	@Autowired
	private AccountService accountService;
	
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		return !accountService.checkExistingAccount(username);
	}

}
