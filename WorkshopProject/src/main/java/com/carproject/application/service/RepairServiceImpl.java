package com.carproject.application.service;

import com.carproject.application.dto.InsertRepairDTO;
import com.carproject.application.dto.InsertRepairVehicleDTO;
import com.carproject.application.dto.RepairDTO;
import com.carproject.application.dto.UpdateRepairDTO;
import com.carproject.application.entity.Repair;
import com.carproject.application.entity.Vehicle;
import com.carproject.application.entity.Workshop;
import com.carproject.application.repository.RepairRepository;
import com.carproject.application.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class RepairServiceImpl implements RepairService{
    @Autowired
    private RepairRepository repairRepository;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private WorkshopService workshopService;

    @Override
    public List<Repair> getRepair(Integer workshopId) {
        List<Repair> repair = repairRepository.findAllByWorkshop_Id(workshopId);

        return repair;
    }

    @Override
    public Repair getRepair(Integer workshopId, Integer repairId) {
       Repair repair = repairRepository.findByWorkshopIdAndRepairId(workshopId, repairId);
       return repair;
    }

    @Override
    public RepairDTO getRepairDTO(Repair repair) {
        RepairDTO response = new RepairDTO();
        response.setId(repair.getId());
        response.setDate(repair.getDate());
        response.setDescription(repair.getDescription());
        response.setPrice(repair.getPrice());
        response.setWorkshopName(repair.getWorkshop().getName());
        response.setDuration(repair.getDuration());
        response.setVehicle(vehicleService.getVehicle(repair.getVehicle()));

        return response;
    }

    @Override
    public Repair createRepair(InsertRepairVehicleDTO dto, Workshop workshop) {

        Vehicle vehicle = new Vehicle(dto.getVehicle().getBrand(), dto.getVehicle().getModel(),
                dto.getVehicle().getPlatNumber(), dto.getVehicle().getYear());
        vehicleRepository.save(vehicle);
        Repair repair = new Repair(
                dto.getDate(), dto.getDescription(), workshop,
                 vehicle, dto.getDuration(), dto.getPrice()
        );
        repairRepository.save(repair);
        return repair;
    }

    @Override
    public Repair createRepair(InsertRepairDTO dto, Workshop workshop) {
        Vehicle vehicle = vehicleRepository.getById(dto.getVehicleId());
        Repair repair = new Repair(
                dto.getDate(), dto.getDescription(), workshop,
                vehicle, dto.getDuration(), dto.getPrice()
        );
        repairRepository.save(repair);
        return repair;
    }

    @Override
    public Repair updateRepair(Repair repair, UpdateRepairDTO dto) {
        repair.setDescription(dto.getDescription());
        repair.setDuration(dto.getDuration());
        repair.setPrice(dto.getPrice());
        repairRepository.save(repair);
        return repair;
    }

    @Override
    public List<Repair> getRepairsByPlatNumber(String platNumber) {
        return repairRepository.getRepairsByPlatNumber(platNumber);
    }
}
