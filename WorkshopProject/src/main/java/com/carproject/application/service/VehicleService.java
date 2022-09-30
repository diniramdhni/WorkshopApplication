package com.carproject.application.service;

import com.carproject.application.dto.VehicleDTO;
import com.carproject.application.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    VehicleDTO getVehicle(Vehicle vehicle);

    Vehicle getById(Integer vehicleId);

    List<Vehicle> getAllVehicles();

    List<VehicleDTO> getVehicleLists(List<Vehicle> allVehicles);

    Vehicle getVehicleByPlateNumber(String platNumber);

    boolean cekPlatNumber(String platNumber);

    Vehicle addVehicle(VehicleDTO dto);
}
