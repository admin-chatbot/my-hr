package com.hr.company.service;

import com.hr.company.model.Employee;
import com.hr.company.reporitory.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {

    public EmployeeRepository getRepository();

    public Employee save(Employee employee);
    public List<Employee> save(List<Employee> employees);

    public List<Employee> getAllEmployee();

    public Optional<Employee> getEmployeeById(Long id);

    public Optional<Employee> getEmployeeByCode(String code);
}
