package com.example.school.schema.type;

import java.util.UUID;

public record Course(UUID id, String name) {

    public static Course from(UUID id, String name) {
        return new Course(id, name);
    }

    public static Course from(UUID id) {
        return new Course(id, null);
    }

}
