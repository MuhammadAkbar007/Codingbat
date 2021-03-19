package uz.pdp.task2_lesson1_modul2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task2_lesson1_modul2.payload.AnswerDto;
import uz.pdp.task2_lesson1_modul2.service.AnswerService;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody AnswerDto answerDto) {
        return answerService.add(answerDto);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return answerService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return answerService.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id, @RequestBody AnswerDto answerDto) {
        return answerService.edit(id, answerDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return answerService.delete(id);
    }
}
