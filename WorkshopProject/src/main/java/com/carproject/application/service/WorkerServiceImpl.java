package com.carproject.application.service;

import com.carproject.application.dto.InsertWorkerDTO;
import com.carproject.application.dto.WorkerDTO;
import com.carproject.application.entity.Worker;
import com.carproject.application.entity.Workshop;
import com.carproject.application.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkerServiceImpl implements WorkerService{

    @Autowired
    private WorkerRepository workerRepository;

    @Override
    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    @Override
    public WorkerDTO getWorker(Worker worker) {
        WorkerDTO response = new WorkerDTO();
        response.setId(worker.getId());
        response.setFirstName(worker.getFirstName());
        response.setLastName(worker.getLastName());
        response.setWorkshopName(worker.getWorkshop().getName());
        return response;
    }

    @Override
    public Worker addWorker(InsertWorkerDTO dto, Workshop workshop) {
        Worker worker = new Worker(dto.getFirstName(), dto.getLastName(), workshop
        );

        workerRepository.save(worker);
        return worker;
    }

    @Override
    public List<WorkerDTO> getWorkerList(List<Worker> allWorkers) {
        return allWorkers.stream().map(this::getWorker).collect(Collectors.toList());

    }

    @Override
    public Worker getWorkerById(Integer workerId) {
        Optional<Worker> workerOptional = workerRepository.findById(workerId);
        Worker worker = null;
        if(workerOptional.isPresent()){
            worker = workerOptional.get();
        }
        return worker;
    }
}
