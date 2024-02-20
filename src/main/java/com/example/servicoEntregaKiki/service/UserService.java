package com.example.servicoEntregaKiki.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.servicoEntregaKiki.model.User;
import com.example.servicoEntregaKiki.repository.OrderRepository;
import com.example.servicoEntregaKiki.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderDtoRepository;

    public User findByMyId(Long id) {

        Optional<User> user = this.userRepository.findById(id);

        return user.orElseThrow(() -> new RuntimeException(
                "usuario com ID: " + id + " não encontrado " + " Tipo: " + User.class.getName()));
    }

    @Transactional
    public User newUser(User obj) {
        obj.setId(null);
        obj = this.userRepository.save(obj);
        this.orderDtoRepository.saveAll(obj.getOrders());

        return obj;
    }

    @Transactional
    public User updateUser(User obj) {

        User newObj = findByMyId(obj.getId());
        newObj.setPassword(obj.getPassword());

        return this.userRepository.save(newObj);
    }

    public void delete(long id) {
        findByMyId(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Não é possivel excluir pois há Serviços relacionados!");
        }
    }

}
