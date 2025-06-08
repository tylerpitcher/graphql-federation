package com.example.school.type.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
interface CourseRepository extends JpaRepository<Course, UUID> {
    List<Course> findAllByStudents_Id(UUID studentId);
}
