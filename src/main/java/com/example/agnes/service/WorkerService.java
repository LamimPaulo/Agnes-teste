package com.example.agnes.service;

import com.example.agnes.model.Worker;
import com.example.agnes.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    final WorkerRepository workerRepository;
    @Autowired
    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public List<Worker> getAll(){
        return workerRepository.findAll();
    }

    public Optional<Worker> getById(Long id){
        return workerRepository.findById(id);
    }

    public Worker save(Worker worker){
        return workerRepository.save(worker);
    }

    public void deleteById(Long id){
        workerRepository.deleteById(id);
    }
}
