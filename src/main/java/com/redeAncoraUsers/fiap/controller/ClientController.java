package com.redeAncoraUsers.fiap.controller;

import com.redeAncoraUsers.fiap.model.Client;
import com.redeAncoraUsers.fiap.repository.ClientRepository;
import com.redeAncoraUsers.fiap.validators.ClientGetData;
import com.redeAncoraUsers.fiap.validators.ClientPostData;
import com.redeAncoraUsers.fiap.validators.ClientUpdateData;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientRepository repository;

    public ClientController(ClientRepository repository) { this.repository = repository; }

    @PostMapping
    @Transactional
    public void PostClient(@RequestBody @Valid ClientPostData data){
        repository.save(new Client(data));
    }

    @PutMapping
    @Transactional
    public void updateClient(@RequestBody @Valid ClientUpdateData data){
        Client client = repository.getReferenceById(data.id());
        client.updateInfo(data);
        repository.save(client);
    }

     @GetMapping
     public List<Client> getClient(){
         return repository.findAll();
     }

     @GetMapping("/{id}")
        public List<ClientGetData> getClientById(@PathVariable Long id){
            return repository.findById(id).stream().map(ClientGetData::new).toList();
        }
}
