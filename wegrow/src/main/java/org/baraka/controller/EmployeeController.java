
package org.baraka.controller;

import org.baraka.dto.EmployeeRequest;
import org.baraka.dto.EmployeeResponse;
import org.baraka.entity.Employee;
import org.baraka.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/departments/{departmentId}/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(
            @PathVariable Long departmentId,
            @RequestBody EmployeeRequest request) {

        Employee employee = new Employee();
        employee.setMatricule(request.getMatricule());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setPosition(request.getPosition());
        employee.setHireDate(request.getHireDate());

        Employee created = employeeService.createEmployee(
                departmentId,
                employee
        );

        EmployeeResponse response = toResponse(created);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getEmployeesByDepartment(
            @PathVariable Long departmentId) {

        List<EmployeeResponse> response =
                employeeService.getEmployeesByDepartment(departmentId)
                        .stream()
                        .map(this::toResponse)
                        .toList();

        return ResponseEntity.ok(response);
    }

    private EmployeeResponse toResponse(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getMatricule(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPosition(),
                employee.getHireDate(),
                employee.getDepartment().getId(),
                employee.getDepartment().getName()
        );
    }
}
