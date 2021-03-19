package uz.pdp.task2_lesson1_modul2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.task2_lesson1_modul2.entity.Category;
import uz.pdp.task2_lesson1_modul2.entity.Task;
import uz.pdp.task2_lesson1_modul2.payload.ApiResponse;
import uz.pdp.task2_lesson1_modul2.payload.TaskDto;
import uz.pdp.task2_lesson1_modul2.repository.CategoryRepository;
import uz.pdp.task2_lesson1_modul2.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public ResponseEntity<?> add(TaskDto taskDto) {
        if (taskRepository.existsByNameAndText(taskDto.getName(), taskDto.getText()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Such task already exists !", false));
        Optional<Category> optionalCategory = categoryRepository.findById(taskDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return ResponseEntity.status(404).body(new ApiResponse("Category not found !", false));
        taskRepository.save(new Task(taskDto.getName(), taskDto.getText(), taskDto.getExamples(), taskDto.getSolution(), optionalCategory.get(), taskDto.getHasStar()));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Task successfully added !", true));
    }

    public ResponseEntity<?> getAll() {
        List<Task> all = taskRepository.findAll();
        if (all.isEmpty()) return ResponseEntity.status(404).body(new ApiResponse("No tasks to show !", false));
        return ResponseEntity.ok(all);
    }

    public ResponseEntity<?> getById(Integer id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) return ResponseEntity.status(404).body("Task by this id not found !");
        return ResponseEntity.ok(optionalTask.get());
    }

    public ResponseEntity<?> edit(Integer id, TaskDto taskDto) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) return ResponseEntity.status(404).body("Task by this id not found !");
        Optional<Category> optionalCategory = categoryRepository.findById(taskDto.getCategoryId());
        if (!optionalCategory.isPresent()) return ResponseEntity.status(404).body("Category not found !");
        Task task = optionalTask.get();
        task.setName(taskDto.getName());
        task.setText(taskDto.getText());
        task.setExamples(taskDto.getExamples());
        task.setSolution(taskDto.getSolution());
        task.setHasStar(taskDto.getHasStar());
        task.setCategory(optionalCategory.get());
        taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Task successfully updated !");
    }

    public ResponseEntity<?> delete(Integer id) {
        try {
            taskRepository.deleteById(id);
            return ResponseEntity.ok("Task successfully deleted !");
        } catch (Exception e) {
            return ResponseEntity.status(409).body("Error while deleting !");
        }
    }
}
