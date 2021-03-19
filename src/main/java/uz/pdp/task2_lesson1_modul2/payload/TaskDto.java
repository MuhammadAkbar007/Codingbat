package uz.pdp.task2_lesson1_modul2.payload;

import lombok.Data;

@Data
public class TaskDto {

    private String name;

    private String text;

    private String examples;

    private String solution;

    private Boolean hasStar;

    private Integer categoryId;
}
