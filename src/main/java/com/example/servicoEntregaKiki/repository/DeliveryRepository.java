package com.example.servicoEntregaKiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.servicoEntregaKiki.model.DeliveryModel;

public interface DeliveryRepository extends JpaRepository<DeliveryModel, Long> {

}
