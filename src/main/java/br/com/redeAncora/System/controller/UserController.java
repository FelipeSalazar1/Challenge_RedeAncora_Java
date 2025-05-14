package br.com.redeAncora.System.controller;

import br.com.redeAncora.System.model.User;
import br.com.redeAncora.System.repository.UserRepository;
import br.com.redeAncora.System.validators.UserGetData;
import br.com.redeAncora.System.validators.UserPostData;
import br.com.redeAncora.System.validators.UserUpdateData;
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
