package com.carproject.application.service;

import com.carproject.application.dto.WorkshopDTO;
import com.carproject.application.entity.Workshop;

import java.util.List;

public interface WorkshopService {
    List<Workshop> getAllWorkshops();

    List<WorkshopDTO> workshopList(List<Workshop> allWorkshops);

    WorkshopDTO getWorkshop(Workshop workshop);

    Workshop createWorkshop(WorkshopDTO workshopDTO);

    Workshop getById(Integer workshopId);
}
