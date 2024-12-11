package com.harshwkumar.java_todo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.harshwkumar.java_todo.models.TodoList;

public interface TodoListRepository extends JpaRepository<TodoList, Long>  {
    
}
