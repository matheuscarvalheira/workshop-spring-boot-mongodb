package com.matheuscarvalheira.workshopmongo.services;


import com.matheuscarvalheira.workshopmongo.domain.Post;
import com.matheuscarvalheira.workshopmongo.repository.PostRepository;
import com.matheuscarvalheira.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PostService {

    //chamando a camada repository
    @Autowired
    private PostRepository repo;


    public Post findById(String id) {

        Optional<Post> obj = repo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));


    }


}
