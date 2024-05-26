package com.example.agnes.controller;

import com.example.agnes.dto.ClientDTO;
import com.example.agnes.dto.ClientSummaryDTO;
import com.example.agnes.model.Client;
import com.example.agnes.service.ClientService;
import com.example.agnes.util.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientSummaryDTO> getAllClients() {
        return clientService.getAllClients().stream()
                .map(DTOConverter::toClientSummaryDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        Optional<Client> client = Optional.ofNullable(clientService.getClientById(id));
        return client.map(value -> ResponseEntity.ok(DTOConverter.toClientDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {
        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setEmail(clientDTO.getEmail());

        Client savedClient = clientService.saveClient(client);
        return ResponseEntity.ok(DTOConverter.toClientDTO(savedClient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        Client  existingClient = clientService.getClientById(id);
        if (existingClient != null) {
            Client client = existingClient;
            client.setName(clientDTO.getName());
            client.setEmail(clientDTO.getEmail());

            Client updatedClient = clientService.saveClient(client);
            return ResponseEntity.ok(DTOConverter.toClientDTO(updatedClient));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        if (clientService.getClientById(id) != null) {
            clientService.deleteClient(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
