package com.example.servicoEntregaKiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.servicoEntregaKiki.model.User;
import com.example.servicoEntregaKiki.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/newUser")
    public ResponseEntity<String> postUser(@RequestBody User obj) {

        try {
            var result = userService.newUser(obj);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Usuario " + result.getUsername() + " criado com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Nome do usuario já existe s2");
        }

    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id) {

        var result = this.userService.findByUserId(id);

        return result;
    }

    @PutMapping("/update")
    public User putUserPassword(@RequestBody User obj) {

        var result = userService.updateUser(obj);

        return result;
    }

    @DeleteMapping("delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}
