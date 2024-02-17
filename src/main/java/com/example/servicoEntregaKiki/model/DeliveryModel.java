package com.example.servicoEntregaKiki.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "delivery")
public class DeliveryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "deliveryModel", cascade = CascadeType.REMOVE)
    private List<OrderServiceModel> listDeliveryModel;

    public List<OrderServiceModel> getDeliveryModels() {
        return listDeliveryModel;
    }

    public void setDeliveryModel(List<OrderServiceModel> deliveryModels) {
        this.listDeliveryModel = deliveryModels;
    }

}
