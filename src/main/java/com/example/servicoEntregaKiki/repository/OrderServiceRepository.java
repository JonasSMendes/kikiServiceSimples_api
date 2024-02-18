package com.example.servicoEntregaKiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.servicoEntregaKiki.model.User;

public interface OrderServiceRepository extends JpaRepository<User, Long> {

}
