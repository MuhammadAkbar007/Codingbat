package uz.pdp.task2_lesson1_modul2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task2_lesson1_modul2.entity.ProgLang;
import uz.pdp.task2_lesson1_modul2.entity.StarBadge;

public interface StarBadgeRepository extends JpaRepository<StarBadge, Integer> {

    boolean existsByScoreAndProgLang(Integer score, ProgLang progLang);
}
