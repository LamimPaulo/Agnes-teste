package com.example.agnes.util;

import com.example.agnes.dto.ClientDTO;
import com.example.agnes.dto.ClientSummaryDTO;
import com.example.agnes.dto.ProjectDTO;
import com.example.agnes.model.Client;
import com.example.agnes.model.Project;
import com.example.agnes.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DTOConverter {
    private static ClientService clientService;

    @Autowired
    public DTOConverter(ClientService clientService) {
        DTOConverter.clientService = clientService;
    }

    public static ProjectDTO toProjectDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        projectDTO.setStatus(project.getStatus());

        if (project.getClient() != null) {
            projectDTO.setClientId(project.getClient().getId());
            projectDTO.setClient(ClientSummaryDTO.fromClient(project.getClient()));
        }

        return projectDTO;
    }

    public static ProjectDTO toProjectDTOWithoutClient(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        projectDTO.setStatus(project.getStatus());
        projectDTO.setClientId(project.getClient() != null ? project.getClient().getId() : null);

        return projectDTO;
    }

    public ProjectDTO toProjectDTOWithLazyLoadedClient(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        projectDTO.setStatus(project.getStatus());

        if (project.getClient() != null) {
            projectDTO.setClientId(project.getClient().getId());
            Client clientEntity = clientService.getClientById(project.getClient().getId());
            if (clientEntity != null) {
                projectDTO.setClient(ClientSummaryDTO.fromClient(clientEntity));
            }
        }

        return projectDTO;
    }

    public static ClientDTO toClientDTO(Client client) {
        ClientDTO clientDTO = ClientSummaryDTO.fromClient(client);

        if (client.getProjects() != null) {
            clientDTO.setProjects(client.getProjects().stream()
                    .map(DTOConverter::toProjectDTOWithoutClient)
                    .collect(Collectors.toList()));
        }

        return clientDTO;
    }

    public static ClientSummaryDTO toClientSummaryDTO(Client client) {
        return new ClientSummaryDTO(client.getId(), client.getName(), client.getEmail());
    }
}
