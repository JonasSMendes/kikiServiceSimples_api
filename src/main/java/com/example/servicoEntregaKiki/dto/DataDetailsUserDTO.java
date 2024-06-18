package com.example.servicoEntregaKiki.dto;

import com.example.servicoEntregaKiki.domain.User;

public record DataDetailsUserDTO(Long id, String username, String password) {
    public DataDetailsUserDTO(User dados){
        this(dados.getId(), dados.getUsername(), dados.getPassword());
    }
}
