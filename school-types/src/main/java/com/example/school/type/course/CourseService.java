package com.example.school.type.course;

import com.example.school.type.student.Student;
import com.example.school.type.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final StudentService studentService;

    public Course getCourseById(UUID id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course with id of '%s' not found".formatted(id)));
    }

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public List<Course> getCoursesByStudentId(UUID studentId)  {
        return courseRepository.findAllByStudents_Id(studentId);
    }

    public Course createCourse(String name) {
        return courseRepository.save(new Course(name));
    }

    public Course enrollStudentInCourse(UUID studentId, UUID courseId) {
        Student student = studentService.getStudentById(studentId);
        Course course = getCourseById(courseId);

        if (!course.getStudents().contains(student)) {
            course.getStudents().add(student);
        }

        return courseRepository.save(course);
    }

}
