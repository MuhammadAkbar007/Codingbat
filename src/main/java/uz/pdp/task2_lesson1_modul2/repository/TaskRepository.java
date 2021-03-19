package uz.pdp.task2_lesson1_modul2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task2_lesson1_modul2.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    boolean existsByNameAndText(String name, String text);
}
