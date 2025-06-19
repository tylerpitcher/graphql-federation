package com.example.company.team;

import com.example.company.core.BaseEntity;
import com.example.company.employee.Employee;
import jakarta.persistence.*;
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
public class Team extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "membership",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private Set<Employee> members = new HashSet<>();

    public Team(String name) {
        this.name = name;
    }

    private Team(UUID id) {
        this.setId(id);
    }

    public static Team from(String id) {
        return new Team(UUID.fromString(id));
    }

}
