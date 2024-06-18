package com.example.servicoEntregaKiki.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.servicoEntregaKiki.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser_Id(Long userId);

}
