package com.carproject.application.entity;

import javax.persistence.*;

@Entity
@Table(name = "Worker")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "LastName")
    private String lastName;
    @ManyToOne
    @JoinColumn(name = "WorkShopId")
    private Workshop workshop;

    public Worker(){}

    public Worker(String firstName, String lastName, Workshop workshop) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.workshop = workshop;
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

    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }
}
