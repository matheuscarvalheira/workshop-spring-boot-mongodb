package com.matheuscarvalheira.workshopmongo.services;


import com.matheuscarvalheira.workshopmongo.domain.Post;
import com.matheuscarvalheira.workshopmongo.repository.PostRepository;
import com.matheuscarvalheira.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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

    public List<Post> findByTitle(String text){
        return repo.searchTitle(text);
    }

    public List<Post> fullSearch(String text, Date minDate, Date  maxDate){
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);

        return repo.fullSearch(text,minDate,maxDate);
    }

}
