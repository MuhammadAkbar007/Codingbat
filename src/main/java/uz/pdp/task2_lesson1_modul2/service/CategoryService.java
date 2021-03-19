package uz.pdp.task2_lesson1_modul2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.task2_lesson1_modul2.entity.Category;
import uz.pdp.task2_lesson1_modul2.entity.ProgLang;
import uz.pdp.task2_lesson1_modul2.payload.ApiResponse;
import uz.pdp.task2_lesson1_modul2.payload.CategoryDto;
import uz.pdp.task2_lesson1_modul2.repository.CategoryRepository;
import uz.pdp.task2_lesson1_modul2.repository.ProglangRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProglangRepository proglangRepository;

    public ResponseEntity<?> add(CategoryDto categoryDto) {
        Optional<ProgLang> optionalProgLang = proglangRepository.findById(categoryDto.getProgLangId());
        if (!optionalProgLang.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Programming language by this id npt found !", false));
        if (categoryRepository.existsByNameAndProgLang(categoryDto.getName(), optionalProgLang.get()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Such category already exists !", false));
        categoryRepository.save(new Category(categoryDto.getName(), categoryDto.getDescription(), optionalProgLang.get()));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Category successfully added !", true));
    }

    public ResponseEntity<?> getAll() {
        List<Category> all = categoryRepository.findAll();
        if (all.isEmpty()) return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("No category to show !", false));
        return ResponseEntity.ok(all);
    }

    public ResponseEntity<?> getByProglang(Integer progLangId) {
        Optional<ProgLang> optionalProgLang = proglangRepository.findById(progLangId);
        if (!optionalProgLang.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Programming language by this id not found !", false));
        Optional<Category> optionalCategory = categoryRepository.findByProgLang(optionalProgLang.get());
        if(!optionalCategory.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Category by this programming language not found !", false));
        return ResponseEntity.ok(optionalCategory.get());
    }

    public ResponseEntity<?> edit(Integer id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Category by this id not found !", false));
        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        Optional<ProgLang> optionalProgLang = proglangRepository.findById(categoryDto.getProgLangId());
        if (!optionalProgLang.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Programming language by this id not found !", false));
        category.setProgLang(optionalProgLang.get());
        categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse("Category successfully edited !", true));
    }

    public ResponseEntity<?> delete(Integer id) {
        try {
            categoryRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("Category successfully deleted !", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Category by this id not found !", true));
        }
    }
}
