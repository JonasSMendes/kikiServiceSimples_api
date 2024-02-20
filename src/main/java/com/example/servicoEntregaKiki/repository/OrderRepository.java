package com.example.servicoEntregaKiki.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.servicoEntregaKiki.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByOrderDTOId(Long id);

    List<Order> findbyUser_Id(Long id);

    // @Query(value = "SELECT * FROM orders o WHERE o.user_id = :id", nativeQuery =
    // true)
    // List<OrderDTO> findByUserid(@Param("id") Long id);
}
