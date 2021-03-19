package uz.pdp.task2_lesson1_modul2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.task2_lesson1_modul2.entity.ProgLang;
import uz.pdp.task2_lesson1_modul2.entity.StarBadge;
import uz.pdp.task2_lesson1_modul2.payload.ApiResponse;
import uz.pdp.task2_lesson1_modul2.payload.StarBadgeDto;
import uz.pdp.task2_lesson1_modul2.repository.ProglangRepository;
import uz.pdp.task2_lesson1_modul2.repository.StarBadgeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StarBadgeService {

    @Autowired
    StarBadgeRepository starBadgeRepository;

    @Autowired
    ProglangRepository proglangRepository;

    public ResponseEntity<?> add(StarBadgeDto starBadgeDto) {
        Optional<ProgLang> optionalProgLang = proglangRepository.findById(starBadgeDto.getProgLangId());
        if (!optionalProgLang.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Programming language by this id not found !", false));
        if (starBadgeRepository.existsByScoreAndProgLang(starBadgeDto.getScore(), optionalProgLang.get()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Such star badge already exists !", false));
        starBadgeRepository.save(new StarBadge(starBadgeDto.getScore(), optionalProgLang.get()));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Star badge successfully added !", true));
    }

    public ResponseEntity<?> getAll() {
        List<StarBadge> all = starBadgeRepository.findAll();
        if (all.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse("No star badges to show !", false));
        return ResponseEntity.ok(all);
    }

    public ResponseEntity<?> getById(Integer id) {
        Optional<StarBadge> optionalStarBadge = starBadgeRepository.findById(id);
        if (!optionalStarBadge.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Star badge by this id not found !", false));
        return ResponseEntity.ok(optionalStarBadge.get());
    }

    public ResponseEntity<?> edit(Integer id, StarBadgeDto starBadgeDto) {
        Optional<StarBadge> optionalStarBadge = starBadgeRepository.findById(id);
        if (!optionalStarBadge.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Star badge by this id not found !", false));
        Optional<ProgLang> optionalProgLang = proglangRepository.findById(starBadgeDto.getProgLangId());
        if (!optionalProgLang.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Programming language by this id not found !", false));
        StarBadge starBadge = optionalStarBadge.get();
        starBadge.setScore(starBadgeDto.getScore());
        starBadge.setProgLang(optionalProgLang.get());
        starBadgeRepository.save(starBadge);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse("Star badge successfully updated !", true));
    }

    public ResponseEntity<?> delete(Integer id) {
        try {
            starBadgeRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("Star badge successfully deleted !", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Star badge by this id not found !", false));
        }
    }
}
