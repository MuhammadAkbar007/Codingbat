package uz.pdp.task2_lesson1_modul2.payload;

import lombok.Data;

@Data
public class AnswerDto {

    private String body;

    private Boolean isTrue;

    private Integer taskId;

    private Integer userId;
}
