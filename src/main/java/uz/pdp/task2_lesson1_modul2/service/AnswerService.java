package uz.pdp.task2_lesson1_modul2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.task2_lesson1_modul2.entity.Answer;
import uz.pdp.task2_lesson1_modul2.entity.Task;
import uz.pdp.task2_lesson1_modul2.entity.User;
import uz.pdp.task2_lesson1_modul2.payload.AnswerDto;
import uz.pdp.task2_lesson1_modul2.repository.AnswerRepository;
import uz.pdp.task2_lesson1_modul2.repository.TaskRepository;
import uz.pdp.task2_lesson1_modul2.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<?> add(AnswerDto answerDto) {
        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (!optionalTask.isPresent()) return ResponseEntity.status(404).body("Task not found !");
        if (answerRepository.existsByBodyAndTask(answerDto.getBody(), optionalTask.get()))
            return ResponseEntity.status(409).body("Such answer already exists !");
        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (!optionalUser.isPresent()) return ResponseEntity.status(404).body("User not found !");
        answerRepository.save(new Answer(answerDto.getBody(), answerDto.getIsTrue(), optionalTask.get(), optionalUser.get()));
        return ResponseEntity.status(201).body("Answer successfully added !");
    }

    public ResponseEntity<?> getAll() {
        List<Answer> all = answerRepository.findAll();
        if (all.isEmpty()) return ResponseEntity.status(404).body("No answer to show !");
        return ResponseEntity.ok(all);
    }

    public ResponseEntity<?> getById(Integer id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (!optionalAnswer.isPresent()) return ResponseEntity.status(404).body("Answer by this id not found !");
        return ResponseEntity.ok(optionalAnswer.get());
    }

    public ResponseEntity<?> edit(Integer id, AnswerDto answerDto) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (!optionalAnswer.isPresent()) return ResponseEntity.status(404).body("Answer by this id not found !");
        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (!optionalTask.isPresent()) return ResponseEntity.status(404).body("Task not found !");
        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (!optionalUser.isPresent()) return ResponseEntity.status(404).body("User not found !");
        Answer answer = optionalAnswer.get();
        answer.setBody(answerDto.getBody());
        answer.setTrue(answerDto.getIsTrue());
        answer.setTask(optionalTask.get());
        answer.setUser(optionalUser.get());
        answerRepository.save(answer);
        return ResponseEntity.status(202).body("Answer successfully updated !");
    }

    public ResponseEntity<?> delete(Integer id) {
        try {
            answerRepository.deleteById(id);
            return ResponseEntity.ok("Answer successfully deleted !");
        } catch (Exception e) {
            return ResponseEntity.status(409).body("Error while deleting !");
        }
    }
}
