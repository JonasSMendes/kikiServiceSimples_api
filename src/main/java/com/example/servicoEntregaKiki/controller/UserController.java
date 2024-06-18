package com.example.servicoEntregaKiki.controller;


import com.example.servicoEntregaKiki.dto.DataDetailsUserDTO;
import com.example.servicoEntregaKiki.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.servicoEntregaKiki.domain.service.UserService;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity newUser(@RequestBody @Valid UserDTO dados){
        var result = userService.newUser(dados);
        return ResponseEntity.ok(result);
    }

}
