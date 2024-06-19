package com.example.servicoEntregaKiki.domain.service;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

import com.example.servicoEntregaKiki.dto.DataAttOrdersDTO;
import com.example.servicoEntregaKiki.dto.DataDetailsOrderDTO;
import com.example.servicoEntregaKiki.dto.OrderDTO;
import com.example.servicoEntregaKiki.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.servicoEntregaKiki.domain.Order;
import com.example.servicoEntregaKiki.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public DataDetailsOrderDTO newOrder(OrderDTO dados) {
       var user = userRepository.findById(dados.user_id())
               .orElseThrow(() -> new RuntimeException("id not found"));

        var order = new Order(user, dados.service());
        orderRepository.save(order);

        return new DataDetailsOrderDTO(order);
    }


    public Page<DataDetailsOrderDTO> findOrderById(Long id, Pageable pageable) {
        if (!userRepository.existsById(id)){
            throw new RuntimeException("user id not found");
        }

        Page<Order> orderList = orderRepository.findByUser_Id(id, pageable);
        
        Page<DataDetailsOrderDTO> result = orderList
                .map(DataDetailsOrderDTO::new);
        
        return result;
    }

    public Page<DataDetailsOrderDTO> allOrders(Pageable pageable) {
        return orderRepository.findAll(pageable)
                .map(DataDetailsOrderDTO::new);
    }

    public void delete(Long id) {
        var result = orderRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("order not found"));

        orderRepository.delete(result);
    }

    public DataDetailsOrderDTO updateOrder(DataAttOrdersDTO dados) {
        var order = orderRepository.findById(dados.id())
                .orElseThrow(()-> new RuntimeException("id not found"));

        order.setService(dados.service());
        orderRepository.save(order);

        return new DataDetailsOrderDTO(order);
    }
}
