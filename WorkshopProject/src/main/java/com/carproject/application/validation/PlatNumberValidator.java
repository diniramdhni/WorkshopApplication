package com.carproject.application.validation;

import com.carproject.application.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PlatNumberValidator implements ConstraintValidator<PlatNumber, String> {

    @Autowired
    private VehicleService vehicleService;

    @Override
    public boolean isValid(String platNumber, ConstraintValidatorContext constraintValidatorContext) {
        return !vehicleService.cekPlatNumber(platNumber);
    }
}
