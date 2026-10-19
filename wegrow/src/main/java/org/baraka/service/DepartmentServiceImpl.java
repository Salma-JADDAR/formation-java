
package org.baraka.service;

import org.baraka.entity.Department;
import org.baraka.exception.BusinessException;
import org.baraka.exception.ResourceNotFoundException;
import org.baraka.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department createDepartment(Department department) {
        if (department.getName() == null || department.getName().isBlank()) {
            throw new BusinessException(
                    "Department name is required."
            );
        }

        String name = department.getName().trim();
        department.setName(name);

        if (departmentRepository.existsByName(name)) {
            throw new BusinessException(
                    "A department with this name already exists."
            );
        }

        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department not found with id: " + id
                ));

        departmentRepository.delete(department);
    }
}
