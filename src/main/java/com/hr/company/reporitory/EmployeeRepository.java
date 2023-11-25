package com.hr.company.reporitory;

import com.hr.company.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    public Optional<Employee> findEmployeeByCode(String code);


}
