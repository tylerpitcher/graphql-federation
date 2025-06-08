package com.example.school.type.course;

import com.example.school.type.core.BaseEntity;
import com.example.school.type.student.Student;
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
public class Course extends BaseEntity {

    private String name;

    @ManyToMany
    @JoinTable(
            name = "course_enrollment",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students = new ArrayList<>();

    public Course(String name) {
        this.name = name;
    }

}

