package com.example.school.course;

import com.example.school.type.course.Course;
import com.example.school.type.course.CourseService;
import com.example.school.type.student.Student;
import com.example.school.type.student.StudentService;
import com.netflix.graphql.dgs.*;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@DgsComponent
public class CourseResolver {

    private final CourseService courseService;
    private final StudentService studentService;

    public CourseResolver(CourseService courseService, StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @DgsQuery
    public List<Course> courses() {
        return courseService.getCourses();
    }

    @DgsQuery
    public Course course(@InputArgument UUID id) {
        return courseService.getCourseById(id);
    }

    @DgsMutation
    public Course createCourse(@InputArgument String name) {
        return courseService.createCourse(name);
    }

    @DgsMutation
    public Course enrollStudentInCourse(
            @InputArgument UUID studentId,
            @InputArgument UUID courseId
    ) {
        return courseService.enrollStudentInCourse(studentId, courseId);
    }

    @DgsEntityFetcher(name = "Student")
    @Transactional(readOnly = true)
    public Student resolveStudent(Map<String, Object> values) {
        UUID id = UUID.fromString((String) values.get("id"));
        return studentService.getStudentById(id);
    }

    @DgsData(parentType = "Student", field = "courses")
    @Transactional(readOnly = true)
    public List<Course> coursesForStudent(DgsDataFetchingEnvironment dfe) {
        Student student = dfe.getSource();
        return courseService.getCoursesByStudentId(student.getId());
    }

}
