package com.example.ayrotekchallange.service;

import com.example.ayrotekchallange.domain.Client;
import com.example.ayrotekchallange.entity.ClientEntity;
import com.example.ayrotekchallange.error.ErrorCodes;
import com.example.ayrotekchallange.exception.AyrotekException;
import com.example.ayrotekchallange.mapper.ClientMapper;
import com.example.ayrotekchallange.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public Client addClient(Client newClient) {

        validateClient(newClient);

        validateClientNameIsUnique(newClient.getName());

        ClientEntity client = clientRepository.save(clientMapper.toEntity(newClient));

        return clientMapper.toDomainObject(client);
    }

    public Client updateClient(String clientId, Client updatedClient) {
        validateClient(updatedClient);

        if (clientRepository.existsById(clientId) && !clientRepository.findById(clientId).get().getName().equals(updatedClient.getName())) {
            validateClientNameIsUnique(updatedClient.getName());
        }

        Client existingClient = clientMapper.toDomainObject(clientRepository.findById(clientId).orElseThrow(() -> new AyrotekException(ErrorCodes.DATA_NOT_FOUND)));


        existingClient.setName(updatedClient.getName());

        ClientEntity client = clientRepository.save(clientMapper.toEntity(existingClient));
        return clientMapper.toDomainObject(client);
    }

    public void deleteClient(String clientId) {
        ClientEntity existingClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new AyrotekException(ErrorCodes.DATA_NOT_FOUND));

        clientRepository.delete(existingClient);
    }

    public List<Client> getAllClients() {
        return clientMapper.toListDomainObject(clientRepository.findAll());
    }

    private void validateClient(Client client) {
        if (Objects.isNull(client)) {
            throw new AyrotekException(ErrorCodes.DATA_NOT_FOUND);
        }
        if (!StringUtils.hasText(client.getName())) {
            throw new AyrotekException(ErrorCodes.CLIENT_NAME_CAN_NOT_BE_EMPTY);
        }
    }

    private void validateClientNameIsUnique(String clientName) {
        if (clientRepository.existsByName(clientName)) {
            throw new AyrotekException(ErrorCodes.CLIENT_NAME_NOT_UNIQUE);
        }
    }

    public Client getById(String id){
        return clientMapper.toDomainObject(clientRepository.findById(id).orElseThrow(() -> new AyrotekException(ErrorCodes.DATA_NOT_FOUND)));
    }


}
