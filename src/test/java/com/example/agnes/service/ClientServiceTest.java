package com.example.agnes.service;

import com.example.agnes.model.Client;
import com.example.agnes.repository.ClientRepository;
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

class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        client = new Client();
        client.setId(1L);
        client.setName("Test Client");
        client.setEmail("testclient@example.com");
    }

    @Test
    void get_all_clients() {
        List<Client> clients = new ArrayList<>();
        clients.add(client);

        when(clientRepository.findAll()).thenReturn(clients);

        List<Client> result = clientService.getAllClients();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void get_client_by_id() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        Client result = clientService.getClientById(1L);
        assertNotNull(result);
        assertEquals("Test Client", result.getName());
        verify(clientRepository, times(1)).findById(1L);
    }

    @Test
    void get_client_by_invalid_id() {
        when(clientRepository.findById(2L)).thenReturn(Optional.empty());

        Client result = clientService.getClientById(2L);
        assertNull(result);
        verify(clientRepository, times(1)).findById(2L);
    }

    @Test
    void save_clients() {
        when(clientRepository.save(client)).thenReturn(client);

        Client result = clientService.saveClient(client);
        assertNotNull(result);
        assertEquals("Test Client", result.getName());
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void save_client_with_null_id() {
        Client result = clientService.saveClient(null);
        assertNull(result);
        verify(clientRepository, times(1)).save(any());
    }

    @Test
    void delete_client_by_invalid_id() {
        when(clientRepository.findById(5L)).thenReturn(Optional.empty());

        clientService.deleteClient(5L);

        verify(clientRepository, times(1)).deleteById(5L);
    }

    @Test
    void delete_clients() {
        doNothing().when(clientRepository).deleteById(1L);

        clientService.deleteClient(1L);
        verify(clientRepository, times(1)).deleteById(1L);
    }
}
