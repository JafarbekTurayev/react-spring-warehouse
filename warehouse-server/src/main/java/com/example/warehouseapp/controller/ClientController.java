package com.example.warehouseapp.controller;

import com.example.warehouseapp.entity.Client;
import com.example.warehouseapp.payload.ApiResponse;
import com.example.warehouseapp.payload.responce.ClientDto;
import com.example.warehouseapp.repository.ClientRepository;
import com.example.warehouseapp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;
    @Autowired
    ClientRepository clientRepository;

    @PostMapping
    public HttpEntity<?> addClient(@RequestBody ClientDto clientDto){
        ApiResponse responseClient = clientService.edd(clientDto);
        return ResponseEntity.ok(responseClient);
    }
    @GetMapping("{phoneNumber}")
    public HttpEntity<?>getphoneNumber(@PathVariable String phoneNumber){
        boolean phoneNumberClient = clientRepository.existsByPhoneNumber(phoneNumber);
        if (!phoneNumberClient) {
            return ResponseEntity.ok("Not Found");
        }
        return  ResponseEntity.ok(((String.valueOf(phoneNumberClient))));
    }
    @GetMapping("/all")
    public HttpEntity<?> getAll(){
        List<Client> clients = clientRepository.findAll();
        return ResponseEntity.ok(clients);
    }
    @DeleteMapping("{id}")
    public HttpEntity<?>remove(@PathVariable Integer id){
        if (!clientRepository.findById(id).isPresent()){return ResponseEntity.ok("Not Fount");}
        clientRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
    @PutMapping("{id}")
    public HttpEntity<?>editClient(@PathVariable Integer id,@RequestBody ClientDto clientDto){
        ApiResponse responseEdit =  clientService.edit(id,clientDto);
        return ResponseEntity.status(responseEdit.isSuccess() ? 200 : 404).body(responseEdit);
    }

    //client Id barcha sotib olgan narsalari history
    //Axror
}
