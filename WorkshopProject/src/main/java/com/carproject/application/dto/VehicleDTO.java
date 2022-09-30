package com.carproject.application.dto;

import com.carproject.application.validation.PlatNumber;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class VehicleDTO {

    private Integer id;
    @NotBlank(message = "Not Blank")
    @NotNull(message = "Not Null")
    private String brand;
    @NotBlank(message = "Not Blank")
    @NotNull(message = "Not Null")
    private String model;
    @PlatNumber(message = "Plat Number already exist in database")
    private String platNumber;
    @NotNull(message = "Not Null")
    private Integer year;

    public VehicleDTO(){}

    public VehicleDTO(Integer id, String brand, String model, String platNumber, Integer year) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.platNumber = platNumber;
        this.year = year;
    }

    public VehicleDTO(String brand, String model, String platNumber, Integer year) {
        this.brand = brand;
        this.model = model;
        this.platNumber = platNumber;
        this.year = year;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public String getPlatNumber() {
        return platNumber;
    }

    public void setPlatNumber(String platNumber) {
        this.platNumber = platNumber;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
