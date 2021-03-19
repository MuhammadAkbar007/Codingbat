package uz.pdp.task2_lesson1_modul2.payload;

import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
public class StarBadgeDto {

    private Integer id;

    private Integer score;

    private Integer progLangId;
}
