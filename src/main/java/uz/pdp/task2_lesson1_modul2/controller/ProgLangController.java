package uz.pdp.task2_lesson1_modul2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task2_lesson1_modul2.entity.ProgLang;
import uz.pdp.task2_lesson1_modul2.service.ProgLangService;

@RestController
@RequestMapping("/api/progLang")
public class ProgLangController {

    @Autowired
    ProgLangService progLangService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody ProgLang progLang) {
        return progLangService.add(progLang);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return progLangService.getAll();
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        return progLangService.getByName(name);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id, @RequestBody ProgLang progLang) {
        return progLangService.edit(id, progLang);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return progLangService.delete(id);
    }
}
