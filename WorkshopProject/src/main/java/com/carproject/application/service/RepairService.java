package com.carproject.application.service;

import com.carproject.application.dto.InsertRepairDTO;
import com.carproject.application.dto.InsertRepairVehicleDTO;
import com.carproject.application.dto.RepairDTO;
import com.carproject.application.dto.UpdateRepairDTO;
import com.carproject.application.entity.Repair;
import com.carproject.application.entity.Workshop;

import java.util.List;

public interface RepairService {
    List<Repair> getRepair(Integer workshopId);

    Repair getRepair(Integer workshopId, Integer repairId);

    RepairDTO getRepairDTO(Repair repair);

    Repair createRepair(InsertRepairVehicleDTO dto, Workshop workshop);

    Repair createRepair(InsertRepairDTO dto, Workshop workshop);

    Repair updateRepair(Repair repair, UpdateRepairDTO dto);

    List<Repair> getRepairsByPlatNumber(String platNumber);
}
