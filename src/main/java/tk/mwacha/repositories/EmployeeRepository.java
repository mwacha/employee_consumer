package tk.mwacha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.mwacha.entities.Employee;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
}
