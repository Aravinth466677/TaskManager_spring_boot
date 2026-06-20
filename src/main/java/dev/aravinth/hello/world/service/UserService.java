package dev.aravinth.hello.world.service;

import dev.aravinth.hello.world.models.User;
import dev.aravinth.hello.world.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
@Autowired
    private UserRepository repo;

    public User createUser(User user){
        return repo.save(user);
    }

    public User getUserById(long id){
        return repo.findById(id).orElseThrow(()->new RuntimeException("User not found"));
    }
}
