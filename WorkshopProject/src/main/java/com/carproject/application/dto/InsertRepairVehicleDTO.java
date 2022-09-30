package com.carproject.application.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class InsertRepairVehicleDTO {

    private LocalDate date = LocalDate.now(); //waktu pesan pastilah waktu add juga
    @NotBlank(message = "Not Blank")
    @NotNull(message = "Not Null")
    private String description;
    private VehicleDTO vehicle;
    @NotNull(message = "Not Null")
    private Double duration;
    @NotNull(message = "Not Null")
    private BigDecimal price;

    public InsertRepairVehicleDTO(){}


    public InsertRepairVehicleDTO(String description,  VehicleDTO vehicle, Double duration, BigDecimal price) {
        this.description = description;

        this.vehicle = vehicle;
        this.duration = duration;
        this.price = price;
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
}
