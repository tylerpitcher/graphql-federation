package com.example.company.employee;

import com.example.company.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    Set<Employee> findAllByTeams_Id(UUID teamId);

}
