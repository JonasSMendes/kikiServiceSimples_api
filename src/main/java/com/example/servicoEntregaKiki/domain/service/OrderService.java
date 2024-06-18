package com.example.servicoEntregaKiki.domain.service;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

import com.example.servicoEntregaKiki.dto.DataDetailsOrderDTO;
import com.example.servicoEntregaKiki.dto.OrderDTO;
import com.example.servicoEntregaKiki.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Transactional
    public DataDetailsOrderDTO newOrder(OrderDTO dados) {
       var user = userRepository.findById(dados.user_id())
               .orElseThrow(() -> new RuntimeException("id not found"));

        var order = new Order(user, dados.service());
        orderRepository.save(order);

        return new DataDetailsOrderDTO(order);
    }


    public List<DataDetailsOrderDTO> findOrderById(Long id) {
        if (!userRepository.existsById(id)){
            throw new RuntimeException("user id not found");
        }

        List<Order> orderList = orderRepository.findByUser_Id(id);
        List<DataDetailsOrderDTO> result = orderList.stream()
                .map(DataDetailsOrderDTO::new)
                .collect(Collectors.toList());

        return result;
    }


    public List<Order> findAllByUserId(Long userId) {

        List<Order> order = this.orderRepository.findByUser_Id(userId);

        return order;
    }


//    @Transactional
//    public Order updateOrder(Order obj) {
//
//        Order newObj = findOrderById(obj.getId());
//        newObj.setService(obj.getService());
//
//        return this.orderRepository.save(newObj);
//    }

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
