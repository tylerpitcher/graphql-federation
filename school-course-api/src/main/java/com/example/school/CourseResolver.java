package com.example.school;

import com.example.school.schema.type.Course;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;
import java.util.UUID;

@DgsComponent
public class CourseResolver {

    private static final List<Course> courses = List.of(
            new Course(UUID.randomUUID(), "Math"),
            new Course(UUID.randomUUID(), "Science"),
            new Course(UUID.randomUUID(), "History"),
            new Course(UUID.randomUUID(), "Gym"),
            new Course(UUID.randomUUID(), "English")
    );

    @DgsQuery
    public List<Course> courses() {
        return courses;
    }

    @DgsQuery
    public Course course(@InputArgument String id) {
        return courses.stream()
                .filter(course -> course.id().toString().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Course with id '%s' not found".formatted(id)));
    }

}
