package com.carproject.application.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Repair")
public class Repair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Date")
    private LocalDate date;
    @Column(name = "Description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "WorkshopId")
    private Workshop workshop;
    @ManyToOne
    @JoinColumn(name = "VehicleId")
    private Vehicle vehicle;
    @Column(name = "Duration")
    private double duration;  //durasi dalam jam
    @Column(name = "Price")
    private BigDecimal price;

    public Repair(){}

    public Repair(LocalDate date, String description, Workshop workshop, Vehicle vehicle, double duration, BigDecimal price) {
        this.date = date;
        this.description = description;
        this.workshop = workshop;
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

    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
