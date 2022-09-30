package com.carproject.application.controller;

import com.carproject.application.NotFoundException;
import com.carproject.application.dto.RepairDTO;
import com.carproject.application.dto.VehicleDTO;
import com.carproject.application.entity.Repair;
import com.carproject.application.entity.Vehicle;
import com.carproject.application.service.RepairService;
import com.carproject.application.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private RepairService repairService;

    //get all vehicle
    @GetMapping
    public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
        List<VehicleDTO> response = vehicleService.getVehicleLists(vehicleService.getAllVehicles());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //get berdasarkan plat number
    @GetMapping(value = "/{platNumber}")
    public ResponseEntity<VehicleDTO> getVehicleByPlatNumber(@PathVariable(value = "platNumber") String platNumber) {
            Vehicle vehicle = vehicleService.getVehicleByPlateNumber(platNumber);
            if(vehicle == null){
                throw new NotFoundException("Vehicle with plat number " + platNumber + " not found!");
            } else {
                VehicleDTO response = vehicleService.getVehicle(vehicle);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
    }

    //get repair berdasarkan plat number
    @GetMapping(value = "/{platNumber}/repairs")
    public ResponseEntity<List<RepairDTO>> getRepairsByPlateNumber(@PathVariable(value = "platNumber") String platNumber) {
        Vehicle vehicle = vehicleService.getVehicleByPlateNumber(platNumber);

        if(vehicle == null){
            throw new NotFoundException("Vehicle with plat number " + platNumber + " not found!");
        } else {
            List<Repair> repairList =repairService.getRepairsByPlatNumber(platNumber);
            List<RepairDTO> response = repairList
                    .stream()
                    .map(repair -> repairService.getRepairDTO(repair))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    //add vehicle
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> addVehicle(@Valid @RequestBody VehicleDTO dto) {

            Vehicle newVehicle = vehicleService.addVehicle(dto);
            return new ResponseEntity<>(vehicleService.getVehicle(newVehicle), HttpStatus.OK);
    }
}
