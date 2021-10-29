package com.example.restapi.service;

import com.example.restapi.entity.Student;

import java.time.LocalDate;
import java.time.Period;

public class StudentService {
    private String str="Lỗi: ";
    public boolean check(Student student){
            boolean flag = true;
            if(student.getName() == null ){
                str=str+"tên Không được trống. \n";
                flag = false;
            }
            else if(student.getName().length() > 50) {
                str=str+"độ dài tên không phù hợp . \n";
                flag = false;
            }
            if(student.getBirthday() == null){
                str=str+"nam sinh trống \n";
                flag = false;
            }else{
                Period period = Period.between(student.getBirthday().toLocalDate(), LocalDate.now());
                if(period.getYears() > 100 || period.getYears() < 0){
                   str=str+"nam sinh không phù hợp \n";
                    flag = false;
                }
            }
        if(student.getClassname() == null){
            str=str+" tên lớp trống \n";
            flag = false;
        }
        if(student.getMajor() == null){
            str=str+"chuyên ngành trống \n";
            flag = false;
        }
        if (student.getHometown() == null){
            str=str+"quê quán trống\n";
            flag = false;
        }
            if(student.getGender() == null) {
                str=str+"Giới tính trống \n";
                flag = false;
            }else if(
                    !(student.getGender().equalsIgnoreCase("nam") ||
                            student.getGender().equalsIgnoreCase("nu") ||
                            student.getGender().equalsIgnoreCase("khac"))){
                str=str+"giới tính không phù hợp \n";
                flag = false;
            }

        if(student.getMark()==0 ){
            str=str+"điểm trống \n";
            flag = false;
        }
            else if(student.getMark() < 0 || student.getMark() > 10) {
                str=str+"điểm không phù hợp \n";
                flag = false;
            }
            return flag;
        }

        public String getError(){
            return str.toString();
        }
    }
