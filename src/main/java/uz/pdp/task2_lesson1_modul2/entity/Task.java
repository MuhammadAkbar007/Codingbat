package uz.pdp.task2_lesson1_modul2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String text;

    private String examples;

    private String solution;

    @ManyToOne
    private Category category;

    private boolean hasStar;

    public Task(String name, String text, String examples, String solution, Category category, boolean hasStar) {
        this.name = name;
        this.text = text;
        this.examples = examples;
        this.solution = solution;
        this.category = category;
        this.hasStar = hasStar;
    }
}
