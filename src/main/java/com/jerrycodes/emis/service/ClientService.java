package com.jerrycodes.emis.service;

import com.jerrycodes.emis.entity.Client;
import com.jerrycodes.emis.model.ClientDto;

import java.util.List;

public interface ClientService {
    List<Client> findAllClients();

    Client saveClient(ClientDto clientDto);
}
