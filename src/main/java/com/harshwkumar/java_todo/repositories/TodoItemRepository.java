package com.harshwkumar.java_todo.repositories;

import com.harshwkumar.java_todo.models.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long>  {
    
}