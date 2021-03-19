package uz.pdp.task2_lesson1_modul2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String password;

    @ManyToOne
    private StarBadge starBadge;

    public User(String email, String password, StarBadge starBadge) {
        this.email = email;
        this.password = password;
        this.starBadge = starBadge;
    }
}
