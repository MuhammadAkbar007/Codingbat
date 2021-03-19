package uz.pdp.task2_lesson1_modul2.payload;

import lombok.Data;

@Data
public class UserDto {

    private String email;

    private String password;

    private Integer starBadgeId;
}
