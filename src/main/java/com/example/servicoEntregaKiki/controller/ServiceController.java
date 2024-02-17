package com.example.servicoEntregaKiki.controller;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.servicoEntregaKiki.exception.NotFoundOrderServiceException;
import com.example.servicoEntregaKiki.model.OrderServiceModel;
import com.example.servicoEntregaKiki.model.dto.OrderServiceDTO;
import com.example.servicoEntregaKiki.repository.DeliveryRepository;
import com.example.servicoEntregaKiki.repository.OrderServiceRepository;

@RestController
@RequestMapping("kikiService")
public class ServiceController {

    private OrderServiceRepository orderServiceRepository;

    private DeliveryRepository deliveryRepository;

    public ServiceController(OrderServiceRepository orderServiceRepository, DeliveryRepository deliveryRepository) {
        this.orderServiceRepository = orderServiceRepository;
        this.deliveryRepository = deliveryRepository;
    }

    @PostMapping("order")
    public ResponseEntity<OrderServiceDTO> createOrder(@RequestBody OrderServiceDTO orderService) {

        OrderServiceModel orderServiceObj = orderService.toOrderServiceModel();
        orderServiceRepository.save(orderServiceObj);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("allOrders")
    public ResponseEntity<List<OrderServiceModel>> getAllOrders() {
        List<OrderServiceModel> orderList = orderServiceRepository.findAll();

        if (!orderList.isEmpty()) {
            for (OrderServiceModel order : orderList) {
                Long id = order.getId();
                Link selfLink = WebMvcLinkBuilder.linkTo(ServiceController.class)
                        .slash("order")
                        .slash(id)
                        .withSelfRel();
                order.add(selfLink);
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(orderList);
    }

    @GetMapping("{userOrdersID}")
    public ResponseEntity<OrderServiceDTO> getOrderID(@PathVariable(value = "userOrdersID") Long userOrdersID) {

        OrderServiceModel orderService = this.orderServiceRepository.findById(userOrdersID)
                .orElseThrow(
                        () -> new NotFoundOrderServiceException("nennhuma pessoa encontrada com o id " + userOrdersID));

        return ResponseEntity.ok().body(new OrderServiceDTO(orderService));
    }

}
