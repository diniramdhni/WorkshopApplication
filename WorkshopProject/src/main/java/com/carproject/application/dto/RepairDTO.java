package com.carproject.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RepairDTO {
    private Integer id;
    private LocalDate date;
    private String description;
    private String workshopName;
    private VehicleDTO vehicle;
    private Double duration;
    private BigDecimal price;

    public RepairDTO(){}

    public RepairDTO(Integer id, LocalDate date, String description, String workshopName, VehicleDTO vehicle, Double duration, BigDecimal price) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.workshopName = workshopName;
        this.vehicle = vehicle;
        this.duration = duration;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VehicleDTO getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleDTO vehicle) {
        this.vehicle = vehicle;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getWorkshopName() {
        return workshopName;
    }

    public void setWorkshopName(String workshopName) {
        this.workshopName = workshopName;
    }
}
