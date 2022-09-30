package com.carproject.application.service;

import com.carproject.application.dto.WorkshopDTO;
import com.carproject.application.entity.Workshop;
import com.carproject.application.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkshopServiceImpl implements WorkshopService{

    @Autowired
    private WorkshopRepository workshopRepository;

    @Override
    public List<Workshop> getAllWorkshops() {
        return workshopRepository.findAll();
    }

    @Override
    public WorkshopDTO getWorkshop(Workshop workshop) {
        WorkshopDTO response = new WorkshopDTO();
        response.setId(workshop.getId());
        response.setName(workshop.getName());
        return response;
    }

    @Override
    public Workshop createWorkshop(WorkshopDTO workshopDTO) {
        Workshop workshop = new Workshop(workshopDTO.getName());
        return workshopRepository.save(workshop);
    }

    @Override
    public Workshop getById(Integer workshopId) {
        Optional<Workshop> workshopOptional = workshopRepository.findById(workshopId);
        Workshop workshop = null;
        if(workshopOptional.isPresent()){
            workshop = workshopOptional.get();
        }
        return workshop;
    }

    @Override
    public List<WorkshopDTO> workshopList(List<Workshop> allWorkshops) {
        return allWorkshops.stream().map(this::getWorkshop).collect(Collectors.toList());
    }
}
