package com.nuzhd.config;


import com.nuzhd.model.ToDoItem;
import com.nuzhd.repository.ToDoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ToDoItemDataLoader implements CommandLineRunner {

    @Autowired
    private ToDoItemRepository toDoItemRepository;

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        if (toDoItemRepository.count() == 0) {
            ToDoItem item = new ToDoItem("Test item");
            ToDoItem item2 = new ToDoItem("Test item #2");
            toDoItemRepository.save(item);
            toDoItemRepository.save(item2);
        }
    }
}
