package com.example.company.employee;

import com.example.company.team.Team;
import com.netflix.graphql.dgs.*;
import lombok.RequiredArgsConstructor;

import java.util.*;

@DgsComponent
@RequiredArgsConstructor
public class EmployeeResolver {

    private final EmployeeService employeeService;

    @DgsQuery
    public List<Employee> employees() {
        return employeeService.getEmployees();
    }

    @DgsQuery
    public Employee employee(@InputArgument UUID id) {
        return employeeService.getEmployeeById(id);
    }

    @DgsMutation
    public Employee createEmployee(@InputArgument String name) {
        return employeeService.createStudent(name);
    }

    @DgsEntityFetcher(name = "Team")
    public Team resolveTeam(Map<String, Object> values) {
        return Team.from((String) values.get("id"));
    }

    @DgsData(parentType = "Team", field = "members")
    public Set<Employee> employeesForTeam(DgsDataFetchingEnvironment dfe) {
        Team team = Objects.requireNonNull(dfe.getSource(), "Team source cannot be null");
        return employeeService.getEmployeesByTeamId(team.getId());
    }

}
