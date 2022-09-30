package com.carproject.application.controller;

import com.carproject.application.NotFoundException;
import com.carproject.application.dto.InsertWorkerDTO;
import com.carproject.application.dto.WorkerDTO;
import com.carproject.application.entity.Worker;
import com.carproject.application.entity.Workshop;
import com.carproject.application.service.WorkerService;
import com.carproject.application.service.WorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @Autowired
    private WorkshopService workshopService;

    //get all workers
    @GetMapping
    public ResponseEntity<List<WorkerDTO>> getAllWorkers() {
        List<WorkerDTO> response = workerService.getWorkerList(workerService.getAllWorkers());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //get worker by its id
    @GetMapping(value = "/{workerId}")
    public ResponseEntity<WorkerDTO> getWorkerByWorkerId(@PathVariable(value = "workerId") Integer workerId) {

        Worker worker = workerService.getWorkerById(workerId);
        if(worker == null) {
            throw new NotFoundException("Worker with Id " + workerId + " not found!");

        } else {
            WorkerDTO response = workerService.getWorker(worker);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    //add worker
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkerDTO> addWorker(@RequestBody InsertWorkerDTO dto) {

        Workshop workshop = workshopService.getById(dto.getWorkshopId());
        if(workshop == null){
            throw new NotFoundException("Workshop with Id " + dto.getWorkshopId() + " not found!");
        } else {

            Worker newWorker = workerService.addWorker(dto, workshop);
            return new ResponseEntity<>(workerService.getWorker(newWorker), HttpStatus.OK);
        }
    }
}
