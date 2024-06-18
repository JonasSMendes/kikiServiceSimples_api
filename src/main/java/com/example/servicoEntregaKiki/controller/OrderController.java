package com.example.servicoEntregaKiki.controller;

import java.util.List;

import com.example.servicoEntregaKiki.dto.DataDetailsOrderDTO;
import com.example.servicoEntregaKiki.dto.OrderDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{id}")
    public ResponseEntity<List<DataDetailsOrderDTO>> getOrder(@PathVariable Long id) {
        var result = orderService.findOrderById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> listOrderById(@PathVariable Long userId) {
        userService.findByUserId(userId);

        List<Order> obj = this.orderService.findAllByUserId(userId);

        return ResponseEntity.ok().body(obj);
    }



//    @PutMapping("/{id}")
//    @Validated
//    public ResponseEntity<Void> update(@Valid @RequestBody Order obj, @PathVariable Long id) {
//
//        obj.setId(id);
//        this.orderService.updateOrder(obj);
//
//        return ResponseEntity.noContent().build();
//    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        orderService.delete(id);

        ResponseEntity.noContent().build();
    }

}
