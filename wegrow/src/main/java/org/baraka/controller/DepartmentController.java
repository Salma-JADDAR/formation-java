
package org.baraka.controller;

import org.baraka.dto.DepartmentRequest;
import org.baraka.dto.DepartmentResponse;
import org.baraka.dto.DepartmentSummaryResponse;
import org.baraka.entity.Department;
import org.baraka.service.DepartmentService;
import org.baraka.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    public DepartmentController(DepartmentService departmentService, EmployeeService employeeService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<DepartmentResponse> createDepartment(
            @RequestBody DepartmentRequest request) {

        Department department = new Department(
                request.getName(),
                request.getDescription()
        );

        Department created = departmentService.createDepartment(department);

        DepartmentResponse response = new DepartmentResponse(
                created.getId(),
                created.getName(),
                created.getDescription()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentSummaryResponse>> getAllDepartments() {

        List<DepartmentSummaryResponse> response =
                departmentService.getAllDepartments()
                        .stream()
                        .map(department -> new DepartmentSummaryResponse(
                                department.getId(),
                                department.getName(),
                                department.getDescription(),
                                employeeService.getEmployeesByDepartment(
                                        department.getId()
                                ).size()
                        ))
                        .toList();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {

        departmentService.deleteDepartment(id);

        return ResponseEntity.noContent().build();
    }
}
