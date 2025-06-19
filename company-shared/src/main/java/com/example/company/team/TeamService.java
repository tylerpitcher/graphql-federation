package com.example.company.team;

import com.example.company.employee.Employee;
import com.example.company.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    private final EmployeeService employeeService;

    public List<Team> getTeams() {
        return teamRepository.findAll();
    }

    public Team getTeamById(UUID id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException("Team with id of '%s' not found".formatted(id)));
    }

    public Set<Team> getTeamsByEmployeeId(UUID studentId)  {
        return teamRepository.findAllByMembers_Id(studentId);
    }

    public Team createTeam(String name) {
        return teamRepository.save(new Team(name));
    }

    public Team addToTeam(UUID employeeId, UUID courseId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        Team team = getTeamById(courseId);

        if (!team.getMembers().contains(employee)) {
            team.getMembers().add(employee);
        }

        return teamRepository.save(team);
    }

}
