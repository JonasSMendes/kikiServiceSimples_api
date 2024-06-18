package com.example.servicoEntregaKiki.dto;

import com.example.servicoEntregaKiki.domain.Order;

public record DataDetailsOrderDTO(Long id, Long user, String service ) {

    public DataDetailsOrderDTO(Order dados){
        this(dados.getId(), dados.getUser().getId(), dados.getService());
    }
}
