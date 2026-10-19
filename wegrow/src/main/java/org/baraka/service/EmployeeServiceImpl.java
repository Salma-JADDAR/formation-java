
package org.baraka.service;

import org.baraka.entity.Department;
import org.baraka.entity.Employee;
import org.baraka.exception.BusinessException;
import org.baraka.exception.ResourceNotFoundException;
import org.baraka.repository.DepartmentRepository;
import org.baraka.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Employee createEmployee(Long departmentId, Employee employee) {
        if (employee.getMatricule() == null || employee.getMatricule().isBlank()) {
            throw new BusinessException("Employee matricule is required.");
        }

        if (employee.getEmail() == null || employee.getEmail().isBlank()) {
            throw new BusinessException("Employee email is required.");
        }

        String matricule = employee.getMatricule().trim();
        String email = employee.getEmail().trim();

        if (employeeRepository.existsByMatricule(matricule)) {
            throw new BusinessException(
                    "An employee with this matricule already exists."
            );
        }

        if (employeeRepository.existsByEmail(email)) {
            throw new BusinessException(
                    "An employee with this email already exists."
            );
        }

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department not found with id: " + departmentId
                ));

        employee.setMatricule(matricule);
        employee.setEmail(email);
        employee.setDepartment(department);

        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getEmployeesByDepartment(Long departmentId) {
        if (!departmentRepository.existsById(departmentId)) {
            throw new ResourceNotFoundException(
                    "Department not found with id: " + departmentId
            );
        }

        return employeeRepository.findByDepartment_Id(departmentId);
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }
}
