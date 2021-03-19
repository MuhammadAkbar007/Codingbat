package uz.pdp.task2_lesson1_modul2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String body;

    private boolean isTrue;

    @ManyToOne
    private Task task;

    @ManyToOne
    private User user;

    public Answer(String body, boolean isTrue, Task task, User user) {
        this.body = body;
        this.isTrue = isTrue;
        this.task = task;
        this.user = user;
    }
}
