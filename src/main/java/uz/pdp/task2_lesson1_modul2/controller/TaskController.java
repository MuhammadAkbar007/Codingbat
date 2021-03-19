package uz.pdp.task2_lesson1_modul2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task2_lesson1_modul2.payload.TaskDto;
import uz.pdp.task2_lesson1_modul2.service.TaskService;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody TaskDto taskDto) {
        return taskService.add(taskDto);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return taskService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return taskService.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id, @RequestBody TaskDto taskDto) {
        return taskService.edit(id, taskDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return taskService.delete(id);
    }
}
