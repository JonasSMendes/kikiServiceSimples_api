package com.example.servicoEntregaKiki.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = Order.TABLE_NAME)
public class Order {

    public static final String TABLE_NAME = "orders";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @Column(name = "service", length = 255, nullable = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String service;

    public Order() {
    }

    public Order(Long id, User user_id, String service) {
        this.id = id;
        this.user = user_id;
        this.service = service;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getService() {
        return this.service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Order id(Long id) {
        setId(id);
        return this;
    }

    public Order user_id(User user_id) {
        setUser(user_id);
        return this;
    }

    public Order service(String service) {
        setService(service);
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;

        int result = 1;

        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());

        return result;
    }

}
