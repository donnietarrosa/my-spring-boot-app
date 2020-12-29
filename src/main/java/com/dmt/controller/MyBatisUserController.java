package com.dmt.controller;

import com.dmt.bean.User;
import com.dmt.exception.ResourceNotFoundException;
import com.dmt.mapper.MyBatisUserMapper;
import com.dmt.repository.JPAUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mybatis/users")
public class MyBatisUserController {

    @Autowired
    private MyBatisUserMapper mapper;

    @GetMapping
    public List<User> getAllUser() {

        return this.mapper.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") long userId) {

        return this.mapper.findById(userId);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {

        return this.mapper.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable(value = "id") long userId) {

        User updatedUser = new User();
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setEmail(user.getEmail());

        return this.mapper.update(updatedUser, userId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") long userId) {

        this.mapper.delete(userId);

        return ResponseEntity.ok().build();
    }
}
