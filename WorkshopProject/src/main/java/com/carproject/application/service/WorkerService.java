package com.carproject.application.service;

import com.carproject.application.dto.InsertWorkerDTO;
import com.carproject.application.dto.WorkerDTO;
import com.carproject.application.entity.Worker;
import com.carproject.application.entity.Workshop;

import java.util.List;

public interface WorkerService {
    List<Worker> getAllWorkers();

    List<WorkerDTO> getWorkerList(List<Worker> allWorkers);

    Worker getWorkerById(Integer workerId);
    WorkerDTO getWorker(Worker worker);

    Worker addWorker(InsertWorkerDTO dto, Workshop workshop);
}
