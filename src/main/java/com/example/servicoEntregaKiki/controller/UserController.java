package com.example.servicoEntregaKiki.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.servicoEntregaKiki.model.User;
import com.example.servicoEntregaKiki.model.User.CreateUSer;
import com.example.servicoEntregaKiki.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @Validated(CreateUSer.class)
    @PostMapping
    public ResponseEntity<Void> postUser(@Valid @RequestBody User obj) {

        try {
            userService.newUser(obj);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(obj.getId()).toUri();

            return ResponseEntity.created(uri).build();

        } catch (RuntimeException e) {

            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {

        var result = this.userService.findByUserId(id);

        return ResponseEntity.ok(result);
    }

    @GetMapping("all")
    public ResponseEntity<List<User>> getAllUser() {

        var result = this.userService.allUser();

        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> update(@Valid @RequestBody User obj, @PathVariable Long id) {

        obj.setId(id);
        this.userService.updateUser(obj);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}
