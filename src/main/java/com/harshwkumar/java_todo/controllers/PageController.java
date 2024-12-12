package com.harshwkumar.java_todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.harshwkumar.java_todo.models.TodoItem;
import com.harshwkumar.java_todo.repositories.TodoItemRepository;

@Controller
public class PageController {

    @Autowired
    private TodoItemRepository todoItemRepository;

    @GetMapping("/")
    public String getWelcome(Model model) {

        TodoItem todoItem = new TodoItem();

        todoItem.setCompleted(false);
        todoItem.setDescription("Hello World");

        todoItemRepository.save(todoItem);

        Iterable<TodoItem> todoItems = todoItemRepository.findAll();

        model.addAttribute("todoItems", todoItems);

        return "welcome";
    }

}