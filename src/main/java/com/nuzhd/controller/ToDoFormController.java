package com.nuzhd.controller;

import com.nuzhd.model.ToDoItem;
import com.nuzhd.repository.ToDoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
public class ToDoFormController {

    @Autowired
    private ToDoItemRepository toDoItemRepository;

    @GetMapping("/create_todo")
    public String showCreateToDoPage(Model model) {
        model.addAttribute("todo", new ToDoItem());
        return "create_todo";
    }

    @PostMapping("/create_todo")
    public String createToDo(@Valid ToDoItem toDoItem, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create_todo";
        }

        toDoItem.setCreated(LocalDateTime.now());
        toDoItem.setModified(LocalDateTime.now());
        toDoItemRepository.save(toDoItem);

        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditPage(@PathVariable(name = "id") Long id, Model model) {
        ToDoItem itemToEdit = toDoItemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ToDo item with id " + id + " not found"));

        model.addAttribute("itemToEdit", itemToEdit);

        return "edit_todo";
    }

    @PostMapping("/edit/{id}")
    public String updateToDo(@PathVariable("id") Long id, @Valid ToDoItem editedItem, BindingResult result) {

        if (result.hasErrors()) {
            editedItem.setId(id);
            return "edit_todo";
        }

        editedItem.setModified(LocalDateTime.now());
        toDoItemRepository.save(editedItem);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteToDo(@PathVariable("id") Long id) {
        ToDoItem itemToDelete = toDoItemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ToDo item with id " + id + " not found"));

        toDoItemRepository.delete(itemToDelete);

        return "redirect:/";
    }

}
