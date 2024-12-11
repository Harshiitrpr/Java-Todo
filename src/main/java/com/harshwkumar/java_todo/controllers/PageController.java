package com.harshwkumar.java_todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.harshwkumar.java_todo.models.TodoItem;
import com.harshwkumar.java_todo.models.TodoList;
import com.harshwkumar.java_todo.repositories.TodoItemRepository;
import com.harshwkumar.java_todo.repositories.TodoListRepository;

@Controller
public class PageController {

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Autowired
    private TodoListRepository todoListRepository;

    @GetMapping("/")
    public ModelAndView getWelcome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("welcome");

        TodoList todoList = new TodoList();
        TodoItem todoItem = new TodoItem();

        todoItem.setTodoList(todoList);
        todoItem.setCompleted(false);
        todoItem.setDescription("Hello World");
        
        todoListRepository.save(todoList);
        todoItemRepository.save(todoItem);

        return modelAndView;
    }
    
}
