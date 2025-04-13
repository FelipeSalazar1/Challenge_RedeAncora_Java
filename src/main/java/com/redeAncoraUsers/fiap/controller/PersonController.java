package com.redeAncoraUsers.fiap.controller;

import com.redeAncoraUsers.fiap.model.PersonEntity;
import com.redeAncoraUsers.fiap.repository.PersonRepository;
import com.redeAncoraUsers.fiap.validators.PersonGetData;
import com.redeAncoraUsers.fiap.validators.PersonPostData;
import com.redeAncoraUsers.fiap.validators.PersonUpdateData;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonRepository repository;

    public PersonController(PersonRepository repository) { this.repository = repository; }

    @PostMapping
    @Transactional
    public void PostPerson(@RequestBody @Valid PersonPostData data){
        repository.save(new PersonEntity(data));
    }

    @PutMapping
    @Transactional
    public void updatePerson(@RequestBody @Valid PersonUpdateData data){
        PersonEntity person = repository.getReferenceById(data.id());
        person.updateInfo(data);
        repository.save(person);
    }

     @GetMapping
     public List<PersonEntity> getPerson(){
         return repository.findAll();
     }

     @GetMapping("/{id}")
        public List<PersonGetData> getPersonById(@PathVariable Long id){
            return repository.findById(id).stream().map(PersonGetData::new).toList();
        }
}
