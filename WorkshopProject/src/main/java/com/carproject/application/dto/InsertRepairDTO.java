package com.carproject.application.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class InsertRepairDTO {
    private LocalDate date = LocalDate.now(); //waktu pesan pastilah waktu add juga
    @NotBlank(message = "Not Blank")
    @NotNull(message = "Not Null")
    private String description;
    @NotNull(message = "Not Null")
    private Integer vehicleId;
    @NotNull(message = "Not Null")
    private Double duration;
    @NotNull(message = "Not Null")
    private BigDecimal price;

    public InsertRepairDTO(){}

    public InsertRepairDTO(LocalDate date, String description, Integer vehicleId, Double duration, BigDecimal price) {
        this.date = date;
        this.description = description;
        this.vehicleId = vehicleId;
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

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
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
