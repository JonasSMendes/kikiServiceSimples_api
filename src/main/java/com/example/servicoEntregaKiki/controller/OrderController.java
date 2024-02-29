package com.example.servicoEntregaKiki.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.servicoEntregaKiki.model.Order;
import com.example.servicoEntregaKiki.service.OrderService;
import com.example.servicoEntregaKiki.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/order")
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {

        Order obj = this.orderService.findOrderById(id);

        return ResponseEntity.ok(obj);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> listOrderById(@PathVariable Long userId) {
        userService.findByUserId(userId);

        List<Order> obj = this.orderService.findAllByUserId(userId);

        return ResponseEntity.ok().body(obj);
    }

    @PostMapping()
    @Validated
    public ResponseEntity<Void> postOrder(@Valid @RequestBody Order obj) {

        orderService.newOrder(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> update(@Valid @RequestBody Order obj, @PathVariable Long id) {

        obj.setId(id);
        this.orderService.updateOrder(obj);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        orderService.delete(id);

        ResponseEntity.noContent().build();
    }

}
