package dev.aravinth.hello.world.controller;

import dev.aravinth.hello.world.SecurityConfig;
import dev.aravinth.hello.world.dto.AuthRequest;
import dev.aravinth.hello.world.models.User;
import dev.aravinth.hello.world.repository.UserRepository;
import dev.aravinth.hello.world.service.UserService;
import dev.aravinth.hello.world.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository repo;
    private final UserService service;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils utils;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest user){
        String email = user.getEmail();
        String password = passwordEncoder.encode(user.getPassword());

        if (repo.findByEmail(email).isPresent()){
            return new ResponseEntity<>("User already exist", HttpStatus.CONFLICT);
        }
        else {
            service.createUser(User.builder().email(email).password(password).build());
            return new ResponseEntity<>("Successfully Registered", HttpStatus.OK);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest user){

        String email = user.getEmail();
        String password = user.getPassword();

        Optional<User> logger = repo.findByEmail(email);

        if (logger.isEmpty()){
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        User detail = logger.get();

        if (!passwordEncoder.matches(password, detail.getPassword())){
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        String token = utils.GenerateToken(email);

        return ResponseEntity.ok(Map.of("token",token));
    }
}
