package dev.aravinth.hello.world.service;

import dev.aravinth.hello.world.models.Todo;
import dev.aravinth.hello.world.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository repo;

    public Todo createtodo(Todo todo){
        todo.setCompleted(false);
        return repo.save(todo);
    }

    public Todo getTodoById(long id){
        return repo.findById(id).orElseThrow(()->new RuntimeException("Todo not found"));
    }

    public List<Todo> getAllTodo(){
        return repo.findAll();
    }

    public Page<Todo> getAllTodoPage(int page, int size){
        Pageable pageable= PageRequest.of(page,size);
        return repo.findAll(pageable);
    }

    public Todo updateTodo(Todo todo){
        return repo.save(todo);
    }

    public void deleteTodoById(Long id){
        repo.delete(getTodoById(id));
    }

    public void deleteTodo(Todo todo){
        repo.delete(todo);
    }
}
