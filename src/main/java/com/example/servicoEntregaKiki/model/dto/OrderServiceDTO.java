package com.example.servicoEntregaKiki.model.dto;

import com.example.servicoEntregaKiki.model.OrderServiceModel;

public class OrderServiceDTO {

    private String username;
    private String pedido;
    private String local;
    private boolean isDelivered = false;

    public OrderServiceDTO(OrderServiceModel orderServiceModel) {
        this.pedido = orderServiceModel.getPedido();
        this.username = orderServiceModel.getUsername();
        this.local = orderServiceModel.getLocal();
        this.isDelivered = orderServiceModel.getIsDelivered();
    }

    public OrderServiceModel toOrderServiceModel() {
        return new OrderServiceModel(pedido, username, local, isDelivered);
    }

    public OrderServiceDTO(String username, String pedido, String local, Boolean isDelivered) {
        this.username = username;
        this.pedido = pedido;
        this.isDelivered = isDelivered;
        this.local = local;

    }

    public static OrderServiceDTO fromOrderServiceModel(OrderServiceModel orderServiceModel) {
        return new OrderServiceDTO(
                orderServiceModel.getUsername(),
                orderServiceModel.getPedido(),
                orderServiceModel.getLocal(),
                orderServiceModel.getIsDelivered());
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
}
