package com.example.servicoEntregaKiki.domain;

import com.example.servicoEntregaKiki.dto.OrderDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity(name = "Order")
@Table(name = Order.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
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

    public Order(User user, String service) {
        this.user = user;
        this.service = service;
    }
}
