package com.example.restapi.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentDto {

    int id;
    String name;
    String birthday;
    String classname;
    String major;
    String hometown;
    String gender;
    int mark;

}
