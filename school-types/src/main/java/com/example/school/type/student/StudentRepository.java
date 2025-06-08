package com.example.school.type.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
interface StudentRepository extends JpaRepository<Student, UUID> {
    List<Student> findAllByCourses_Id(UUID courseId);
}
