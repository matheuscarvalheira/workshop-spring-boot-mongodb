package com.matheuscarvalheira.workshopmongo.config;

import com.matheuscarvalheira.workshopmongo.domain.User;
import com.matheuscarvalheira.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    //chamar o repository
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... arg0) throws Exception {

        userRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria,alex,bob));
    }
}
