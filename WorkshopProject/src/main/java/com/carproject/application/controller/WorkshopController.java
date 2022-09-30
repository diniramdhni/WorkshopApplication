package com.carproject.application.controller;

import com.carproject.application.NotFoundException;
import com.carproject.application.dto.*;
import com.carproject.application.entity.Repair;
import com.carproject.application.entity.Vehicle;
import com.carproject.application.entity.Workshop;
import com.carproject.application.service.RepairService;
import com.carproject.application.service.VehicleService;
import com.carproject.application.service.WorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/workshop")
public class WorkshopController {
    @Autowired
    private WorkshopService workshopService;

    @Autowired
    private RepairService repairService;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<WorkshopDTO>> getAllWorkshops() {
        List<WorkshopDTO> response = workshopService.workshopList(workshopService.getAllWorkshops());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkshopDTO> createWorkshop(@RequestBody WorkshopDTO workshopDTO) {
        WorkshopDTO response = workshopService.getWorkshop(workshopService.createWorkshop(workshopDTO));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //get repair berdasarkan workshop id
    @GetMapping(value = "/{workshopId}/repairs")
    public ResponseEntity<List<RepairDTO>> getRepairs(
            @PathVariable(value = "workshopId") Integer workshopId) {
        //cek workshop id exist or not
        if(workshopService.getById(workshopId) != null) {

            List<Repair> repairList = repairService.getRepair(workshopId);
            List<RepairDTO> response = repairList
                    .stream()
                    .map(repair -> repairService.getRepairDTO(repair))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(response, HttpStatus.OK);

        } else {
            throw new NotFoundException("Id " + workshopId + " from Workshop Not Found!");
        }
    }

    //get repair berdasarkan workshopid dan repair id
    @GetMapping(value = "/{workshopId}/repair/{repairId}")
    public ResponseEntity<RepairDTO> getRepair(
            @PathVariable(value = "workshopId") Integer workshopId,
            @PathVariable(value = "repairId") Integer repairId) {

            Workshop workshop = workshopService.getById(workshopId);
            if (workshop == null) {
                throw new NotFoundException("Id " + workshopId + " from Workshop Not Found!");
            } else {
                Repair repair = repairService.getRepair(workshopId, repairId);
                if (repair == null) {
                    throw new NotFoundException("Id " + repairId + " from Repair Not Found!");
                } else {
                    RepairDTO response = repairService.getRepairDTO(repair);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            }
    }

    //add repair dan vehicle berdasarkan workshopId
    @PostMapping(value = "/{workshopId}/repairVehicle",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RepairDTO> addRepairAndVehicle(@PathVariable(value = "workshopId") Integer workshopId,
            @Valid @RequestBody InsertRepairVehicleDTO dto) {
        Workshop workshop = workshopService.getById(workshopId);
        if (workshop == null) {
            throw new NotFoundException("Id " + workshopId + " from Workshop Not Found!");
        } else {
            if(vehicleService.cekPlatNumber(dto.getVehicle().getPlatNumber()) == true){
                throw new NotFoundException("Plat " + dto.getVehicle().getPlatNumber() + " already exist in database!");
            } else {
                Repair repair = repairService.createRepair(dto, workshop);
                RepairDTO response = repairService.getRepairDTO(repair);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
    }

    //add repair jika data vehicle sudah ada di database
    @PostMapping(value = "/{workshopId}/repair",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RepairDTO> addRepair(
            @PathVariable(value = "workshopId") Integer workshopId,
            @RequestBody InsertRepairDTO dto) {
        Workshop workshop = workshopService.getById(workshopId);
        if (workshop == null) {
            throw new NotFoundException("Id " + workshopId + " from Workshop Not Found!");
        } else {
            if(vehicleService.getById(dto.getVehicleId()) == null){
                throw new NotFoundException("Id " + dto.getVehicleId() + " from Vehicle Not Found!");

            } else {
                Repair repair = repairService.createRepair(dto, workshop);
                RepairDTO response = repairService.getRepairDTO(repair);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
    }

    //update Repair, hanya bisa ubah deskripsi, harga dan durasi kerja
    @PutMapping(value = "/{workshopId}/repair/{repairId}")
    public ResponseEntity<RepairDTO> updateRepair(
            @PathVariable(value = "workshopId") Integer workshopId,
            @PathVariable(value = "repairId") Integer repairId,
            @RequestBody UpdateRepairDTO dto) {
        Workshop workshop = workshopService.getById(workshopId);
        if (workshop == null) {
            throw new NotFoundException("Id " + workshopId + " from Workshop Not Found!");
        } else {
            Repair repair = repairService.getRepair(workshopId, repairId);
            if(repair == null){
                throw new NotFoundException("Id " + repairId + " from Repair in this Workshop Not Found!");
            } else{
                Repair updateRepair = repairService.updateRepair(repair, dto);
                RepairDTO response = repairService.getRepairDTO(updateRepair);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
    }

}
