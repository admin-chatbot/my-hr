package com.hr.company.service.impl;

import com.hr.company.model.Employee;
import com.hr.company.reporitory.EmployeeRepository;
import com.hr.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeRepository getRepository() {
        return employeeRepository;
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> save(List<Employee> employees) {
        return employeeRepository.saveAll(employees);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Optional<Employee> getEmployeeByCode(String code) {
        return employeeRepository.findEmployeeByCode(code);
    }
}
