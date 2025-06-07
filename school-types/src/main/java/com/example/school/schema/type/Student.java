package com.example.school.schema.type;

import java.util.UUID;

public record Student(UUID id, String firstName, String lastName, Integer age) {

    public static Student from(UUID id, String firstName, String lastName, int age) {
        return new Student(id, firstName, lastName, age);
    }

    public static Student from(UUID id) {
        return new Student(id, null, null, null);
    }

}
