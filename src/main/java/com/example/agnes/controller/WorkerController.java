package com.example.agnes.controller;

import com.example.agnes.model.Worker;
import com.example.agnes.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/workers")
public class WorkerController {

    private final WorkerService workerService;
    @Autowired
    public WorkerController(WorkerService workerService){
        this.workerService = workerService;
    }

    @GetMapping
    public List<Worker> getAllWorkers() {
        return workerService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Worker> getWorker(@PathVariable Long id) {
        return workerService.getById(id);
    }

    @PostMapping
    public Worker createWorker(@RequestBody Worker worker) {
        return workerService.save(worker);
    }

    @PutMapping("/{id}")
    public Worker updateWorker(@RequestBody Worker worker) {
        return workerService.save(worker);
    }

    @DeleteMapping("/{id}")
    public void deleteWorker(@PathVariable Long id) {
        workerService.deleteById(id);
    }
}
