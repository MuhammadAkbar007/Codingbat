package uz.pdp.task2_lesson1_modul2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task2_lesson1_modul2.payload.StarBadgeDto;
import uz.pdp.task2_lesson1_modul2.service.StarBadgeService;

@RestController
@RequestMapping("/api/starBadge")
public class StarBadgeController {

    @Autowired
    StarBadgeService starBadgeService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody StarBadgeDto starBadgeDto) {
        return starBadgeService.add(starBadgeDto);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return starBadgeService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return starBadgeService.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id, @RequestBody StarBadgeDto starBadgeDto) {
        return starBadgeService.edit(id, starBadgeDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return starBadgeService.delete(id);
    }
}
