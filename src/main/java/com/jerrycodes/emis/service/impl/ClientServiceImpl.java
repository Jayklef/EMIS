package com.jerrycodes.emis.service.impl;

import com.jerrycodes.emis.entity.Client;
import com.jerrycodes.emis.entity.Employee;
import com.jerrycodes.emis.model.ClientDto;
import com.jerrycodes.emis.repository.ClientRepository;
import com.jerrycodes.emis.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client saveClient(ClientDto clientDto) {

        Employee employee = Employee.builder()
                .build();

        Client client = Client.builder()
                .name(clientDto.getName())
                .accountBalance(clientDto.getAccountBalance())
                .employee(employee)
                .build();
        return clientRepository.save(client);
    }
}
