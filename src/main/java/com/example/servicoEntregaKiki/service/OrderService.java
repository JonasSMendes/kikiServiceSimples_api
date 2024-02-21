package com.example.servicoEntregaKiki.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.servicoEntregaKiki.model.Order;
import com.example.servicoEntregaKiki.model.User;
import com.example.servicoEntregaKiki.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    public Order findOrderById(Long id) {

        Optional<Order> order = orderRepository.findById(id);

        return order.orElseThrow(() -> new RuntimeException(
                "Pedido com ID: " + id + " não encontrado " + " Tipo: " + Order.class.getName()));
    }

    @Transactional
    public Order newOrder(Order obj) {
        User user = this.userService.findByUserId(obj.getUser_id().getId());
        obj.setId(null);
        obj.setUser_id(user);
        obj = this.orderRepository.save(obj);

        return obj;
    }

    @Transactional
    public Order updateOrder(Order obj) {

        Order newObj = findOrderById(obj.getId());
        newObj.setService(obj.getService());

        return this.orderRepository.save(newObj);
    }

    public void delete(Long id) {
        findOrderById(id);

        try {
            this.orderRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Não é possivel excluir pois há serviços relacionados");
        }
    }
}
