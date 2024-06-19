package com.example.servicoEntregaKiki.controller;

import java.util.List;

import com.example.servicoEntregaKiki.dto.DataAttOrdersDTO;
import com.example.servicoEntregaKiki.dto.DataDetailsOrderDTO;
import com.example.servicoEntregaKiki.dto.OrderDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.servicoEntregaKiki.domain.Order;
import com.example.servicoEntregaKiki.domain.service.OrderService;
import com.example.servicoEntregaKiki.domain.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/order")
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping()
    @Validated
    @Transactional
    public ResponseEntity postOrder(@Valid @RequestBody OrderDTO obj) {
        var result = orderService.newOrder(obj);
        return ResponseEntity.ok(result);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<Page<DataDetailsOrderDTO>> getOrder(@PathVariable Long id, Pageable pageable) {
        var result = orderService.findOrderById(id, pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<Page<DataDetailsOrderDTO>> listOrders (@PageableDefault(size = 10) Pageable pageable) {
        var result = orderService.allOrders(pageable);
        return ResponseEntity.ok(result);
    }


    @PutMapping
    @Validated
    @Transactional
    public ResponseEntity<DataDetailsOrderDTO> update(@Valid @RequestBody DataAttOrdersDTO dados) {
         var result = orderService.updateOrder(dados);
         return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable("id") Long id) {
        orderService.delete(id);
        ResponseEntity.noContent().build();
    }

}
