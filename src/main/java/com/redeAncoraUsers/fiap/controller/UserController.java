package com.redeAncoraUsers.fiap.controller;

import com.redeAncoraUsers.fiap.model.User;
import com.redeAncoraUsers.fiap.repository.UserRepository;
import com.redeAncoraUsers.fiap.validators.UserGetData;
import com.redeAncoraUsers.fiap.validators.UserPostData;
import com.redeAncoraUsers.fiap.validators.UserUpdateData;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }


    @PostMapping
    @Transactional
    public void postUser(@RequestBody @Valid UserPostData data){
        User user = new User(data);
        repository.save(user);
    }

    @GetMapping
    public List<UserGetData> getUsers(){
        return repository.findAll().stream().map(UserGetData::new).toList();
    }

    @GetMapping("/active")
    public Page<UserGetData> getActivatedUsers(Pageable pageable) {
        return repository.findAllByActivatedTrue(pageable).map(UserGetData::new);
    }

    @GetMapping("/inactive")
    public Page<UserGetData> getInactiveUsers(Pageable pageable) {
        return repository.findAllByActivatedFalse(pageable).map(UserGetData::new);
    }

    @PutMapping
    @Transactional
    public void updateUser(@RequestBody @Valid UserUpdateData data){
        User user = repository.getReferenceById(data.id());
        user.updateInfo(data);
        user.registerUpdatedAt();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteUser(@PathVariable Long id){
        User user = repository.getReferenceById(id);
        user.deleteUser();
    }
}
