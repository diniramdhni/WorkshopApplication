package com.carproject.application.dto;

import javax.persistence.Column;

public class WorkshopDTO {
    private Integer id;
    private String name;

    public WorkshopDTO(){}

    public WorkshopDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
