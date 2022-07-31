package com.nuzhd.controller;

import com.nuzhd.repository.ToDoItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ToDoItemController {
    private final Logger log = LoggerFactory.getLogger(ToDoItemController.class);

    @Autowired
    private ToDoItemRepository toDoItemRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("todos", toDoItemRepository.findAll());
        return "index";
    }
}
