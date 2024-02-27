package com.example.servicoEntregaKiki.controller;

import java.net.URI;

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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/order")
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/get/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable("id") Long id) {

        var result = this.orderService.findOrderById(id);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/post")
    @Validated
    public ResponseEntity<Order> postOrder(@Valid @RequestBody Order obj) {

        orderService.newOrder(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/update/{id}")
    @Validated
    public ResponseEntity<Void> update(@Valid @RequestBody Order obj, @PathVariable Long id) {

        obj.setId(id);
        this.orderService.updateOrder(obj);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        orderService.delete(id);

        ResponseEntity.noContent().build();
    }
}
