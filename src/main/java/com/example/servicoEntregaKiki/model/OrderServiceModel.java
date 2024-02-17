package com.example.servicoEntregaKiki.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Orders")
public class OrderServiceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String pedido;
    private String local;
    private boolean isDelivered;

    private List<Link> links = new ArrayList<>();

    public void add(Link link) {
        if (this.links == null) {
            this.links = new ArrayList<>();
        }
        this.links.add(link);
    }

    @ManyToOne
    private DeliveryModel deliveryModel;

    public OrderServiceModel(String pedido, String username, String local, Boolean isDelivered) {
        this.pedido = pedido;
        this.username = username;
        this.local = local;
        this.isDelivered = isDelivered;
    }

    public OrderServiceModel() {
    }

    public DeliveryModel getDeliveryModel() {
        return deliveryModel;
    }

    public void setDeliveryModel(DeliveryModel deliveryModel) {
        this.deliveryModel = deliveryModel;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Boolean getIsDelivered() {
        return isDelivered;
    }

    public void setIsDelivered(Boolean isDelivered) {
        this.isDelivered = isDelivered;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public void add(Object linkTo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

}
