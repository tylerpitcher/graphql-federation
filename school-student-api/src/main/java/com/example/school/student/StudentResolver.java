package com.example.school.student;

import com.example.school.type.course.CourseService;
import com.example.school.type.student.Student;
import com.example.school.type.course.Course;
import com.example.school.type.student.StudentService;
import com.netflix.graphql.dgs.*;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@DgsComponent
public class StudentResolver {

    private final StudentService studentService;
    private final CourseService courseService;

    public StudentResolver(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @DgsQuery
    public List<Student> students() {
        return studentService.getStudents();
    }

    @DgsQuery
    public Student student(@InputArgument UUID id) {
        return studentService.getStudentById(id);
    }

    @DgsMutation
    public Student createStudent(
            @InputArgument String firstName,
            @InputArgument String lastName,
            @InputArgument Integer age
    ) {
        return studentService.createStudent(firstName, lastName, age);
    }

    @DgsEntityFetcher(name = "Course")
    @Transactional(readOnly = true)
    public Course resolveCourse(Map<String, Object> values) {
        UUID id = UUID.fromString((String) values.get("id"));
        return courseService.getCourseById(id);
    }

    @DgsData(parentType = "Course", field = "students")
    @Transactional(readOnly = true)
    public List<Student> studentsForCourse(DgsDataFetchingEnvironment dfe) {
        Course course = dfe.getSource();
        return studentService.getStudentsByCourseId(course.getId());
    }

}
