package uz.pdp.task2_lesson1_modul2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StarBadge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer score;

    @OneToOne
    private ProgLang progLang;

    public StarBadge(Integer score, ProgLang progLang) {
        this.score = score;
        this.progLang = progLang;
    }
}
