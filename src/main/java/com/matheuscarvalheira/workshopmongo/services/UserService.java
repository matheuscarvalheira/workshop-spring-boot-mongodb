package com.matheuscarvalheira.workshopmongo.services;

import com.matheuscarvalheira.workshopmongo.domain.User;
import com.matheuscarvalheira.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    //chamando a camada service
    @Autowired
    private UserRepository repo;

    //método para encontrar todos os usuários a partir do banco
    public List<User> findAll(){
        //retorna todos os objetos do banco
        return repo.findAll();
    }

}
