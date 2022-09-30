package com.carproject.application.dto;

import com.carproject.application.entity.Workshop;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class WorkerDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String workshopName;

    public WorkerDTO(){}

    public WorkerDTO(Integer id, String firstName, String lastName, String workshopName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.workshopName = workshopName;
    }

    public String getWorkshopName() {
        return workshopName;
    }

    public void setWorkshopName(String workshopName) {
        this.workshopName = workshopName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

}
