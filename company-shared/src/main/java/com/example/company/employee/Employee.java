package com.example.company.employee;

import com.example.company.core.BaseEntity;
import com.example.company.team.Team;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "members")
    private Set<Team> teams = new HashSet<>();

    public Employee(String name) {
        this.name = name;
    }

    private Employee(UUID id)  {
        this.setId(id);
    }

    public static Employee from(String id) {
        return new Employee(UUID.fromString(id));
    }

}
