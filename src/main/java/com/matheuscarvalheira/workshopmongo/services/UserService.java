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

    public void delete(String id){
        findById(id); //realiza uma busca primeiro
        repo.deleteById(id);
    }

    public User update(User obj){
        //buscar o objeto para atualizar
        User newObj = findById(obj.getId());
        //pegar os dados do obj do parametro e jogar pro obj att (newObj)
        updateData(newObj, obj); //joga os dados do obj para o newObj
        return repo.save(newObj); //salva o novo objeto atualizado no banco
    }

    private void updateData(User newObj, User obj) {
        //joga as coisas do obj  para newObj
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }

    public User fromDTO(UserDTO objDto){
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }



}
