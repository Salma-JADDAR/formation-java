
package org.baraka.service;

import org.baraka.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    Department createDepartment(Department department);

    List<Department> getAllDepartments();

    Optional<Department> getDepartmentById(Long id);

    void deleteDepartment(Long id);
}
