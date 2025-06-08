package com.example.school.type.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Student getStudentById(UUID id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with id of '%s' not found".formatted(id)));
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getStudentsByCourseId(UUID courseId) {
        return studentRepository.findAllByCourses_Id(courseId);
    }

    public Student createStudent(String firstName, String lastName, int age) {
        return studentRepository.save(new Student(firstName, lastName, age));
    }

}
