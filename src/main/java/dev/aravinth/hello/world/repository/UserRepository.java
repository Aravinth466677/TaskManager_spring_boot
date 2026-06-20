package dev.aravinth.hello.world.repository;
import java.util.Optional;
import dev.aravinth.hello.world.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
