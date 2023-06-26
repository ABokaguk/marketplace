package com.marketplace.orderservice.services;

import com.marketplace.orderservice.dto.ClientNotFoundException;
import com.marketplace.orderservice.entity.Client;
import com.marketplace.orderservice.repository.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client findClientById(UUID uuid) throws ClientNotFoundException {
        Optional<Client> client = clientRepository.findById(uuid);

        if (client.isEmpty()) {
            log.error("Пользователь с таким ID: {} не найден", uuid);
            throw new ClientNotFoundException(uuid);
        }
        return client.get();
    }

    public void saveClient(Client client) {
        clientRepository.save(client);
    }
}
