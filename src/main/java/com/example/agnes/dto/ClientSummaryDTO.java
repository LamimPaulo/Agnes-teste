package com.example.agnes.dto;

import com.example.agnes.model.Client;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientSummaryDTO {
    private Long id;
    private String name;
    private String email;

    public ClientSummaryDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public static ClientDTO fromClient(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setName(client.getName());
        clientDTO.setEmail(client.getEmail());
        return clientDTO;
    }

}
