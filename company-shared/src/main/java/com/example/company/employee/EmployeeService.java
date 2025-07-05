package com.example.company.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(UUID id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id of '%s' not found".formatted(id)));
    }


    public Set<Employee> getEmployeesByTeamId(UUID teamId) {
        return employeeRepository.findAllByTeams_Id(teamId);
    }

    public Employee createEmployee(String name) {
        return employeeRepository.save(new Employee(name));
    }

    public Employee deleteEmployee(UUID id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.deleteById(id);
        return employee;
    }

}
