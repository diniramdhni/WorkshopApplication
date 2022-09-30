package com.carproject.application.service;

import com.carproject.application.dto.VehicleDTO;
import com.carproject.application.entity.Vehicle;
import com.carproject.application.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public VehicleDTO getVehicle(Vehicle vehicle) {
        VehicleDTO response = new VehicleDTO();
        response.setId(vehicle.getId());
        response.setBrand(vehicle.getBrand());
        response.setModel(vehicle.getModel());
        response.setPlatNumber(vehicle.getPlatNumber());
        response.setYear(vehicle.getYear());
        return response;
    }

    @Override
    public Vehicle getById(Integer vehicleId) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicleId);
        Vehicle vehicle = null;
        if(vehicleOptional.isPresent()){
            vehicle = vehicleOptional.get();
        }
        return vehicle;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }



    @Override
    public List<VehicleDTO> getVehicleLists(List<Vehicle> allVehicles) {
        return allVehicles.stream().map(this::getVehicle).collect(Collectors.toList());

    }

    @Override
    public Vehicle getVehicleByPlateNumber(String platNumber) {
        return vehicleRepository.getByPlatNumber(platNumber);
    }

    @Override
    public boolean cekPlatNumber(String platNumber) {
        Integer totalExistingPlatNumber = vehicleRepository.count(platNumber);
        return (totalExistingPlatNumber > 0) ? true : false;

    }

    @Override
    public Vehicle addVehicle(VehicleDTO dto) {
        Vehicle vehicle = new Vehicle(dto.getBrand(), dto.getModel(), dto.getPlatNumber(), dto.getYear());
        vehicleRepository.save(vehicle);
        return vehicle;
    }
}
