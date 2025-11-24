
package com.example.employee.service;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public List<Employee> getAll() {
        return repo.findAll();
    }

    public Employee addEmployee(Employee emp) {
        return repo.save(emp);
    }

    public Employee updateEmployee(Long id, Employee emp) {
        return repo.findById(id).map(e -> {
            e.setName(emp.getName());
            e.setRole(emp.getRole());
            e.setSalary(emp.getSalary());
            return repo.save(e);
        }).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public String deleteEmployee(Long id) {
        repo.deleteById(id);
        return "Employee deleted!";
    }
}
