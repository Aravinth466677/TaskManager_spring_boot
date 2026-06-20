package dev.aravinth.hello.world.repository;

import dev.aravinth.hello.world.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface TodoRepository extends JpaRepository<Todo,Long> {
}
