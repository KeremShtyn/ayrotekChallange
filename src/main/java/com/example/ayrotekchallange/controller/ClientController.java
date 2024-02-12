package com.example.ayrotekchallange.controller;

import com.example.ayrotekchallange.api.ClientAPI;
import com.example.ayrotekchallange.domain.Client;
import com.example.ayrotekchallange.dto.ClientDTO;
import com.example.ayrotekchallange.mapper.ClientDTOMapper;
import com.example.ayrotekchallange.service.ClientService;

public class ClientController implements ClientAPI {

    private final ClientService clientService;
    private final ClientDTOMapper clientDTOMapper;

    public ClientController(ClientService clientService, ClientDTOMapper clientDTOMapper) {
        this.clientService = clientService;
        this.clientDTOMapper = clientDTOMapper;
    }


    @Override
    public ClientDTO getById(String id) {
        return clientDTOMapper.toDTO(clientService.getById(id));
    }

    @Override
    public ClientDTO save(ClientDTO clientDTO) {
        Client client = clientDTOMapper.toDomain(clientDTO);
        client = clientService.addClient(client);
        return clientDTOMapper.toDTO(client);
    }

    @Override
    public ClientDTO update(ClientDTO clientDTO) {
        return save(clientDTO);
    }
}
