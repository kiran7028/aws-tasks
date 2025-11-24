
package com.example.employee;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTests {

    @Test
    void testGetAllEmployees() {
        EmployeeRepository repo = Mockito.mock(EmployeeRepository.class);
        Mockito.when(repo.findAll()).thenReturn(
            List.of(new Employee(1L, "Kiran", "DevOps", 80000.0))
        );

        EmployeeService service = new EmployeeService(repo);
        var result = service.getAll();

        assertEquals(1, result.size());
        assertEquals("Kiran", result.get(0).getName());
    }
}
