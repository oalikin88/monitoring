package ru.aos.employees.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aos.employees.entity.Department;

/**
 *
 * @author Oleg Alikin
 */
@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long>{
    boolean existsByCode(String code);
}
