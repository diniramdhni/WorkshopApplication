package com.carproject.application.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class UpdateRepairDTO {
    @NotBlank(message = "Not Blank")
    @NotNull(message = "Not Null")
    private String description;
    @NotNull(message = "Not Null")
    private Double duration;
    @NotNull(message = "Not Null")
    private BigDecimal price;

    public UpdateRepairDTO(){}

    public UpdateRepairDTO(String description, Double duration, BigDecimal price) {
        this.description = description;
        this.duration = duration;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
