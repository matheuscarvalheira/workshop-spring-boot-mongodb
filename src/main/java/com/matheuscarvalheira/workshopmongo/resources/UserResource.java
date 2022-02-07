package com.matheuscarvalheira.workshopmongo.resources;

import com.matheuscarvalheira.workshopmongo.domain.User;
import com.matheuscarvalheira.workshopmongo.dto.UserDTO;
import com.matheuscarvalheira.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    //pegar do serviço
    @Autowired
    private UserService service;

    //lista de usuários
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        //lista de usuário chamado list
        //chama o serviço e guarda os users na lista
        List<User> list = service.findAll();
        List <UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {

        User obj = service.findById(id);

        return ResponseEntity.ok().body(new UserDTO(obj));
    }




}
