package com.carproject.application.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class InsertWorkerDTO {


    @NotBlank(message = "Not Blank")
    @NotNull(message = "Not Null")
    private String firstName;
    @NotBlank(message = "Not Blank")
    @NotNull(message = "Not Null")
    private String lastName;
    @NotNull(message = "Not Null")
    private Integer workshopId;


    public InsertWorkerDTO(String firstName, String lastName, Integer workshopId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.workshopId = workshopId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(Integer workshopId) {
        this.workshopId = workshopId;
    }
}
