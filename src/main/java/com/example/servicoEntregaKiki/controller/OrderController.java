package com.example.servicoEntregaKiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.servicoEntregaKiki.model.Order;
import com.example.servicoEntregaKiki.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/post")
    public Order postOrder(@RequestBody Order obj) {

        var result = orderService.newOrder(obj);

        return result;
    }

    @GetMapping("/get/{id}")
    public Order getOrder(@PathVariable("id") Long id) {

        var result = this.orderService.findOrderById(id);

        return result;
    }

    @PutMapping("/update")
    public Order putOrder(@RequestBody Order obj) {

        var result = orderService.updateOrder(obj);

        return result;
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        orderService.delete(id);
    }
}
