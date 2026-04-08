package ru.aos.employees.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aos.employees.entity.Employee;

/**
 *
 * @author Oleg Alikin
 */
@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>{

    boolean existsByDepartmentId(Long id);
    boolean existsByUsername(String username);
    List<Employee> findByDepartmentId(Long id);
}
