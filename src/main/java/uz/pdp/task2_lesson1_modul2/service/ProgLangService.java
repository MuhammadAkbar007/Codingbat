package uz.pdp.task2_lesson1_modul2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.task2_lesson1_modul2.entity.ProgLang;
import uz.pdp.task2_lesson1_modul2.payload.ApiResponse;
import uz.pdp.task2_lesson1_modul2.repository.ProglangRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProgLangService {

    @Autowired
    ProglangRepository proglangRepository;

    public ResponseEntity<?> add(ProgLang progLang) {
        if (proglangRepository.existsByNameAndDescription(progLang.getName(), progLang.getDescription()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Such programming language already exists !", false));
        proglangRepository.save(new ProgLang(progLang.getName(), progLang.getDescription()));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Programming language successfully added !", true));
    }

    public ResponseEntity<?> getAll() {
        List<ProgLang> all = proglangRepository.findAll();
        if (all.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse("No programming language to show !", false));
        return ResponseEntity.ok(all);
    }

    public ResponseEntity<?> getByName(String name) {
        Optional<ProgLang> optionalProgLang = proglangRepository.findByName(name);
        if (!optionalProgLang.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Programming language by this name not found !", false));
        return ResponseEntity.ok(optionalProgLang.get());
    }

    public ResponseEntity<?> edit(Integer id, ProgLang progLang) {
        Optional<ProgLang> optionalProgLang = proglangRepository.findById(id);
        if (!optionalProgLang.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Programming language by this id not found !", false));
        ProgLang progLang1 = optionalProgLang.get();
        progLang1.setName(progLang.getName());
        progLang1.setDescription(progLang.getDescription());
        proglangRepository.save(progLang1);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse("Programming language successfully updated !", true));
    }

    public ResponseEntity<?> delete(Integer id) {
        try {
            proglangRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("Programming language successfully deleted !", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Error while deleting !", false));
        }
    }
}
