package com.matheuscarvalheira.workshopmongo.resources;

import com.matheuscarvalheira.workshopmongo.domain.User;
import com.matheuscarvalheira.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    //pegar do serviço
    @Autowired
    private UserService service;

    //lista de usuários
    @GetMapping
    public ResponseEntity<List<User>> findAll() {


        //lista de usuário chamado list
        //chama o serviço e guarda os users na lista
        List<User> list = service.findAll();

        return ResponseEntity.ok().body(list);
    }


}
