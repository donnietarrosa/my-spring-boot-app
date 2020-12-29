package com.dmt.controller;

import com.dmt.bean.User;
import com.dmt.exception.ResourceNotFoundException;
import com.dmt.repository.JPAUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class JPAUserController {

    @Autowired
    private JPAUserRepository repository;

    @GetMapping
    public List<User> getAllUser() {

        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") long userId) {

        return this.repository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + userId));
    }

    @PostMapping
    public User createUser(@RequestBody User user) {

        return this.repository.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable(value = "id") long userId) {

        User currentUser = this.repository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + userId));

        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setEmail(user.getEmail());

        return this.repository.save(currentUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") long userId) {

        User currentUser = this.repository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + userId));

        this.repository.delete(currentUser);

        return ResponseEntity.ok().body(currentUser);
    }
}
