package com.matheuscarvalheira.workshopmongo.services;

import com.matheuscarvalheira.workshopmongo.domain.User;
import com.matheuscarvalheira.workshopmongo.dto.UserDTO;
import com.matheuscarvalheira.workshopmongo.repository.UserRepository;
import com.matheuscarvalheira.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

import java.util.List;

@Service
public class UserService {

    //chamando a camada repository
    @Autowired
    private UserRepository repo;

    //método para encontrar todos os usuários a partir do banco
    public List<User> findAll(){
        //retorna todos os objetos do banco
        return repo.findAll();
    }

    public User findById(String id){

        Optional<User> obj = repo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));


    }

    public User insert(User obj){
        return repo.insert(obj);
    }

    public User fromDTO(UserDTO objDto){
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }


}
