package com.example.servicoEntregaKiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.servicoEntregaKiki.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findbyUserId(Long id);
}
