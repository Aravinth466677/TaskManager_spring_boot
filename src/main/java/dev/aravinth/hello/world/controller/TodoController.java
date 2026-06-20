package dev.aravinth.hello.world.controller;

import dev.aravinth.hello.world.service.TodoService;
import dev.aravinth.hello.world.models.Todo;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo/api")
public class TodoController {
    @Autowired
    private TodoService service;

    @PostMapping("/create")
    public ResponseEntity<Todo> body(@RequestBody Todo todo){
        System.out.println(todo);
        return new ResponseEntity<>(service.createtodo(todo), HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "TODO found successfully"),
            @ApiResponse(responseCode = "404" , description = "TODO not founded")
    })
    @GetMapping("/findTodo/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable long id){
        try{
            return new ResponseEntity<>(service.getTodoById(id),HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getTodos(){
        return new ResponseEntity<>(service.getAllTodo(),HttpStatus.OK);
    }

    @GetMapping("/todoPage")
    public ResponseEntity<Page<Todo>> todoPage(@RequestParam int page,@RequestParam int size){
        return new ResponseEntity<>(service.getAllTodoPage(page, size),HttpStatus.OK);
    }
    @PutMapping("/updateTodo")
    public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo){
        return new ResponseEntity<>(service.updateTodo(todo),HttpStatus.OK);
    }

    @DeleteMapping("/deleteTodoById/{id}")
    public void deleteTodoById(@PathVariable long id){
        service.deleteTodoById(id);
    }

    @DeleteMapping("/deleteTodo")
    public void deleteTodo(@RequestBody Todo todo){
        service.deleteTodo(todo);
    }

}
