
package org.baraka.repository;

import org.baraka.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByMatricule(String matricule);

    boolean existsByMatricule(String matricule);

    boolean existsByEmail(String email);

    List<Employee> findByDepartment_Id(Long departmentId);
}
