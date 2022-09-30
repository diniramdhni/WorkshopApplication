package com.carproject.application.entity;


import javax.persistence.*;

@Entity
@Table(name = "Vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Brand")
    private String brand;
    @Column(name = "Model")
    private String model;
    @Column(name = "PlatNumber", unique = true)
    private String platNumber;
    @Column(name = "Year")
    private Integer year;

    public Vehicle(){}

    public Vehicle(String brand, String model, String platNumber, Integer year) {
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

    public String getPlatNumber() {
        return platNumber;
    }

    public void setPlatNumber(String platNumber) {
        this.platNumber = platNumber;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
