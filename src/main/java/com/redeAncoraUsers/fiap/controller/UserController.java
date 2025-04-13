package com.redeAncoraUsers.fiap.controller;

import com.redeAncoraUsers.fiap.user.User;
import com.redeAncoraUsers.fiap.user.UserPostData;
import com.redeAncoraUsers.fiap.user.UserRepository;
import com.redeAncoraUsers.fiap.user.UserUpdateData;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public void postUser(@RequestBody @Valid UserPostData data){
        repository.save(new User(data));
    }

    @GetMapping
    public List<User> getUsers(){
        return repository.findAll();
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
