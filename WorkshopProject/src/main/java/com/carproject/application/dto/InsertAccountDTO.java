package com.carproject.application.dto;

import com.carproject.application.validation.UniqueUsername;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class InsertAccountDTO {
    @NotBlank(message = "Not Blank")
    @NotNull(message = "Not Null")
    @UniqueUsername(message = "Another account has used this username!")
    private String username;
    @NotBlank(message = "Not Blank")
    @NotNull(message = "Not Null")
    private String password;
    @NotBlank(message = "Not Blank")
    @NotNull(message = "Not Null")
    private String role;

    public InsertAccountDTO(){}

    public InsertAccountDTO(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
