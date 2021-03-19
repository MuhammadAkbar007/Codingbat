package uz.pdp.task2_lesson1_modul2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task2_lesson1_modul2.entity.ProgLang;

import java.util.Optional;

public interface ProglangRepository extends JpaRepository<ProgLang, Integer> {

    boolean existsByNameAndDescription(String name, String description);

    Optional<ProgLang> findByName(String name);
}
