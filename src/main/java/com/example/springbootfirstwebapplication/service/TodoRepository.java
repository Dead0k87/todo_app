package com.example.springbootfirstwebapplication.service;

import com.example.springbootfirstwebapplication.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    //      service.retrieveTodos(name)
    List<Todo> findByUser(String user);


    //--- these methods are proivded by JPA REPOSITORY
    // service.deleteTodo(id)
    // service.retrieveTodo(id)
    //service.updateTodo(todo)
    // service(addTodo(getLoggedInUserName(model), todo.getDesc(),todo.getTargetDate...
}
