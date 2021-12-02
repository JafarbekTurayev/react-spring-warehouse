package com.example.warehouseapp.service;

import com.example.warehouseapp.entity.Client;
import com.example.warehouseapp.payload.ApiResponse;
import com.example.warehouseapp.payload.responce.ClientDto;
import com.example.warehouseapp.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public ApiResponse edd(ClientDto clientDto) {

        Client client = new Client();
        client.setName(clientDto.getName());
        client.setPhoneNumber(clientDto.getPhoneNumber());
        Client save = clientRepository.save(client);

        return new  ApiResponse("Saved",true);
    }

    public ApiResponse edit(Integer id, ClientDto clientDto) {
        if (!clientRepository.existsById(id)){
            return new ApiResponse("Not Found ",false);
        }

        Optional<Client> optionalClient = clientRepository.findById(id);

        optionalClient.get().setName(clientDto.getName());
        optionalClient.get().setPhoneNumber(clientDto.getPhoneNumber());
        Client save = clientRepository.save(optionalClient.get());
        return new ApiResponse("Edited",true);


    }
}
