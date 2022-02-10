package com.matheuscarvalheira.workshopmongo.resources;

import com.matheuscarvalheira.workshopmongo.domain.User;
import com.matheuscarvalheira.workshopmongo.dto.UserDTO;
import com.matheuscarvalheira.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {

        User obj = service.fromDTO(objDto); //converte DTO para User
        obj = service.insert(obj); //guarda no banco de dados

        //pega o endereço do novo obj inserido
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build(); //retorna 201
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {

        service.delete(id);
        //response com código 204 -> retorna nada
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id) {

        User obj = service.fromDTO(objDto); //converte DTO para User
        obj.setId(id);
        obj = service.update(obj); //guarda no banco de dados

        return ResponseEntity.noContent().build();
    }

}
