package uz.pdp.task2_lesson1_modul2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task2_lesson1_modul2.entity.Answer;
import uz.pdp.task2_lesson1_modul2.entity.Task;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    boolean existsByBodyAndTask(String body, Task task);
}
