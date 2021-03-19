package uz.pdp.task2_lesson1_modul2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task2_lesson1_modul2.payload.CategoryDto;
import uz.pdp.task2_lesson1_modul2.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody CategoryDto categoryDto) {
        return categoryService.add(categoryDto);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/{progLangId}")
    public ResponseEntity<?> getByProgLang(@PathVariable Integer progLangId) {
        return categoryService.getByProglang(progLangId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id, @RequestBody CategoryDto categoryDto) {
        return categoryService.edit(id, categoryDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return categoryService.delete(id);
    }
}
