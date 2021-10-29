package com.example.restapi.entity;

import com.example.restapi.dto.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@AllArgsConstructor
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = true)
    private String name;
    @Column(name = "birthday", nullable = true)
    private Date birthday;
    @Column(name = "classname", nullable = true)
    private String classname;
    @Column(name = "major", nullable = true)
    private String major;
    @Column(name = "hometown", nullable = true)
    private String hometown;
    @Column(name = "gender", nullable = true)
    private String gender;
    @Column(name = "mark", nullable = true)
    private int mark;


    public Student(StudentDto studentDto) {
        this.id = studentDto.getId();
        this.name = studentDto.getName();
        this.birthday = Date.valueOf(studentDto.getBirthday());
        this.classname = studentDto.getClassname();
        this.major = studentDto.getMajor();
        this.hometown = studentDto.getHometown();
        this.gender = studentDto.getGender();
        this.mark = studentDto.getMark();
    }
}


