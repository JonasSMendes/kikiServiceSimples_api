package com.example.servicoEntregaKiki.domain.service;

import java.util.Optional;
import java.util.List;

import com.example.servicoEntregaKiki.dto.DataDetailsUserDTO;
import com.example.servicoEntregaKiki.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.servicoEntregaKiki.domain.User;
import com.example.servicoEntregaKiki.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public DataDetailsUserDTO newUser(UserDTO dados) {

        var user = new User(dados.username(), dados.password());
        userRepository.save(user);

        return new DataDetailsUserDTO(user);
    }

    public Optional<User> login(String username, String password) {
        Optional<User> user = userRepository.findByusername(username);

        if (user != null && user.get().getPassword().equals(password)) {
            return user;
        }

        return null;
    }

    public User findByUserId(Long id) {

        Optional<User> user = this.userRepository.findById(id);

        return user.orElseThrow(() -> new RuntimeException(
                "usuario com ID: " + id + " não encontrado " + " Tipo: " + User.class.getName()));
    }



    public List<User> allUser() {
        return this.userRepository.findAll();
    }

    @Transactional
    public User updateUser(User obj) {

        User newObj = findByUserId(obj.getId());
        newObj.setPassword(obj.getPassword());

        return this.userRepository.save(newObj);
    }

    public void delete(long id) {
        findByUserId(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Não é possivel excluir pois há Serviços relacionados!");
        }
    }

}
