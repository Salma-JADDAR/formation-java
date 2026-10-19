
package org.baraka.service;

import org.baraka.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee createEmployee(Long departmentId, Employee employee);

    List<Employee> getEmployeesByDepartment(Long departmentId);

    Optional<Employee> getEmployeeById(Long id);
}
