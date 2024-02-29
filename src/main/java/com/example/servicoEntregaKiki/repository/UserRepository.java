package com.example.servicoEntregaKiki.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.servicoEntregaKiki.model.User;
import java.util.List;
import com.example.servicoEntregaKiki.model.Order;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    Optional<User> findByusername(String username);
}
