package com.example.school;

import com.example.school.schema.type.Course;
import com.example.school.schema.type.Student;
import com.netflix.graphql.dgs.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@DgsComponent
public class StudentResolver {

    private static final List<Student> students = List.of(
            new Student(UUID.randomUUID(), "Emma", "Johnson", 17),
            new Student(UUID.randomUUID(), "Liam", "Smith", 16),
            new Student(UUID.randomUUID(), "Olivia", "Brown", 17),
            new Student(UUID.randomUUID(), "Noah", "Davis", 15),
            new Student(UUID.randomUUID(), "Ava", "Martinez", 18)
    );

    @DgsQuery
    public List<Student> students() {
        return students;
    }

    @DgsQuery
    public Student student(@InputArgument UUID id) {
        return students().stream()
                .filter(student -> student.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Course with id '%s' not found".formatted(id)));
    }

    @DgsEntityFetcher(name = "Course")
    public Course resolveCourse(Map<String, Object> values) {
        String idString = (String) values.get("id");
        UUID id = UUID.fromString(idString);
        return Course.from(id);
    }

    @DgsData(parentType = "Course", field = "students")
    public List<Student> reviewsFetcher(DgsDataFetchingEnvironment dfe) {
        return students;
    }

}
