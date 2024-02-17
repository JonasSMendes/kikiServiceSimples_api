package com.example.servicoEntregaKiki.model.dto;

import java.util.List;

import com.example.servicoEntregaKiki.model.OrderServiceModel;

public class DeliveryDTO {

    private List<OrderServiceModel> listDeliveryModel;
    private Long id;
    private String username;
    private String pedido;
    private String local;
    private boolean isDelivered = false;

    public List<OrderServiceModel> getDeliveryModels() {
        return listDeliveryModel;
    }

    public void setDeliveryModel(List<OrderServiceModel> deliveryModels) {
        this.listDeliveryModel = deliveryModels;
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
}
