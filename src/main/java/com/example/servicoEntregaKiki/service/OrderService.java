package com.example.servicoEntregaKiki.service;

import java.util.Optional;
import java.util.List;

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

        @SuppressWarnings("null")
        Optional<Order> order = orderRepository.findById(id);

        return order.orElseThrow(() -> new RuntimeException(
                "Pedido com ID: " + id + " não encontrado " + " Tipo: " + Order.class.getName()));
    }

    public List<Order> findAllByUserId(Long userId) {

        List<Order> order = this.orderRepository.findByUser_Id(userId);

        return order;
    }

    @Transactional
    public Order newOrder(Order obj) {

        if (obj.getUser() == null) {
            throw new IllegalArgumentException("Order does not have a user associated with it.");
        }

        User user = this.userService.findByUserId(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);
        obj = this.orderRepository.save(obj);

        if (user == null) {
            throw new RuntimeException("User not found for the provided user ID.");
        }

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
