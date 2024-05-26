package com.example.agnes.service;

import com.example.agnes.model.Worker;
import com.example.agnes.repository.WorkerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WorkerServiceTest {

    @Mock
    private WorkerRepository workerRepository;

    @InjectMocks
    private WorkerService workerService;

    private Worker worker;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        worker = new Worker();
        worker.setId(1L);
        worker.setFirstName("John");
        worker.setLastName("Doe");
        worker.setRole("Developer");
    }

    @Test
    void getAll() {
        List<Worker> workers = new ArrayList<>();
        workers.add(worker);

        when(workerRepository.findAll()).thenReturn(workers);

        List<Worker> result = workerService.getAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(workerRepository, times(1)).findAll();
    }

    @Test
    void getById() {
        when(workerRepository.findById(1L)).thenReturn(Optional.of(worker));

        Optional<Worker> result = workerService.getById(1L);
        assertTrue(result.isPresent());
        assertEquals("John", result.get().getFirstName());
        assertEquals("Doe", result.get().getLastName());
        assertEquals("Developer", result.get().getRole());
        verify(workerRepository, times(1)).findById(1L);
    }

    @Test
    void save() {
        when(workerRepository.save(worker)).thenReturn(worker);

        Worker result = workerService.save(worker);
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("Developer", result.getRole());
        verify(workerRepository, times(1)).save(worker);
    }

    @Test
    void deleteById() {
        doNothing().when(workerRepository).deleteById(1L);

        workerService.deleteById(1L);
        verify(workerRepository, times(1)).deleteById(1L);
    }
}
