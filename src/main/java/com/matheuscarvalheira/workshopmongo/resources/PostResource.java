package com.matheuscarvalheira.workshopmongo.resources;

import com.matheuscarvalheira.workshopmongo.domain.Post;
import com.matheuscarvalheira.workshopmongo.resources.util.URL;
import com.matheuscarvalheira.workshopmongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    //pegar do serviço
    @Autowired
    private PostService service;

    //lista de usuários

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {

        Post obj = service.findById(id);

        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue = "") String text) {

        //pega a string codificada
        //descodifica a mensagem
        text  = URL.decodeParam(text);
        //retorna a  lista de posts com a texto coincidindo com o  que eu dei no parametro
        List<Post> list = service.findByTitle(text);

        return ResponseEntity.ok().body(list);
    }


    @GetMapping("/fullsearch")
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value="text", defaultValue = "") String text,
            @RequestParam(value="minDate", defaultValue = "") String minDate,
            @RequestParam(value="maxDate", defaultValue = "") String maxDate
    ) {

        text  = URL.decodeParam(text);
        Date min  = URL.convertDate(minDate, new Date(0L));
        Date max  = URL.convertDate(maxDate, new Date());

        List<Post> list = service.fullSearch(text,min,max);

        return ResponseEntity.ok().body(list);
    }


}
