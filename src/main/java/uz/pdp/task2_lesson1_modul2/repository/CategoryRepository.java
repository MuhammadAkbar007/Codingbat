package uz.pdp.task2_lesson1_modul2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task2_lesson1_modul2.entity.Category;
import uz.pdp.task2_lesson1_modul2.entity.ProgLang;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    boolean existsByNameAndProgLang(String name, ProgLang progLang);

    Optional<Category> findByProgLang(ProgLang progLang);
}
