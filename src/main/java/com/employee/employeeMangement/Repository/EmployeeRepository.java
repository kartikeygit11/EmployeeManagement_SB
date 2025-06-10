package com.employee.employeeMangement.Repository;

import com.employee.employeeMangement.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
