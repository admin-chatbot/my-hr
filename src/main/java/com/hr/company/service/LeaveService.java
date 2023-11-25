package com.hr.company.service;

import com.hr.company.model.Leave;
import com.hr.company.reporitory.EmployeeRepository;
import com.hr.company.reporitory.LeaveRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface LeaveService {

    public LeaveRepository getRepository();

    public Leave addDefaultLeaves(String employeeCode);

    public Optional<Leave> getLeaveByCode(String code);
}
