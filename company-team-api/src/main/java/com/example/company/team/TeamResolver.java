package com.example.company.team;

import com.example.company.employee.Employee;
import com.netflix.graphql.dgs.*;
import lombok.RequiredArgsConstructor;

import java.util.*;

@DgsComponent
@RequiredArgsConstructor
public class TeamResolver {

    private final TeamService teamService;

    @DgsQuery
    public List<Team> teams() {
        return teamService.getTeams();
    }

    @DgsQuery
    public Team team(@InputArgument UUID id) {
        return teamService.getTeamById(id);
    }

    @DgsMutation
    public Team createTeam(@InputArgument String name) {
        return teamService.createTeam(name);
    }
    @DgsMutation
    public Team addToTeam (@InputArgument  UUID employeeId, @InputArgument UUID teamId) {
        return teamService.addToTeam(employeeId, teamId);
    }

    @DgsEntityFetcher(name = "Employee")
    public Employee resolveEmployee(Map<String, Object> values) {
        return Employee.from((String) values.get("id"));
    }

    @DgsData(parentType = "Employee", field = "teams")
    public Set<Team> teamsForEmployee(DgsDataFetchingEnvironment dfe) {
        Employee employee = Objects.requireNonNull(dfe.getSource(), "Employee source cannot be null");
        return teamService.getTeamsByEmployeeId(employee.getId());
    }

}
