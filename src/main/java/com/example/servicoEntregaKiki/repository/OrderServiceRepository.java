package com.example.servicoEntregaKiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.servicoEntregaKiki.model.OrderServiceModel;

public interface OrderServiceRepository extends JpaRepository<OrderServiceModel, Long> {

}
