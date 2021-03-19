package uz.pdp.task2_lesson1_modul2.payload;

import lombok.Data;

@Data
public class CategoryDto {

    private String name;

    private String description;

    private Integer progLangId;
}
