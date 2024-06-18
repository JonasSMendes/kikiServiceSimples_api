package com.example.servicoEntregaKiki.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "User")
@Table(name = User.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    public static final String TABLE_NAME = "users";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "username", length = 100, nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = 60, nullable = false)
    private String password;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
