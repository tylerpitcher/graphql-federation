package com.example.school.type.student;

import com.example.school.type.core.BaseEntity;
import com.example.school.type.course.Course;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student extends BaseEntity {

    private String firstName;
    private String lastName;
    private Integer age;

    @ManyToMany(mappedBy = "students")
    private List<Course> courses = new ArrayList<>();

    public Student(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

}

