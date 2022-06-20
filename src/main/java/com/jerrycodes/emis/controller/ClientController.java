package com.jerrycodes.emis.controller;

import com.jerrycodes.emis.entity.Client;
import com.jerrycodes.emis.model.ClientDto;
import com.jerrycodes.emis.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clients")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/lists")
    public ResponseEntity<List<Client>> getAllClients(){
        List<Client> clients = clientService.findAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Client> saveClient(@RequestBody ClientDto clientDto){
        Client newClient = clientService.saveClient(clientDto);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }
}
