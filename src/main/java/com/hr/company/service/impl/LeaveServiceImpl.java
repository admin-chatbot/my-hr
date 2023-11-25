package com.hr.company.service.impl;

import com.hr.company.model.Leave;
import com.hr.company.reporitory.LeaveRepository;
import com.hr.company.service.LeaveService;
import com.hr.company.utils.StaticData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Override
    public LeaveRepository getRepository() {
        return leaveRepository;
    }

    @Override
    public Leave addDefaultLeaves(String employCode) {
        Leave leave = new Leave();
        Integer totalLeave = StaticData.getLeaveType().values().stream().mapToInt(Integer::intValue).sum();
        leave.setTotalLeave(totalLeave);
        leave.setCode(employCode);
        leave.setAvailedLeave(0);
        leave.setLeaveYear("2023");
        leave = leaveRepository.save(leave);
        leave.setTypeOfLeaves(StaticData.getLeaveType());
        return leave;
    }

    @Override
    public Optional<Leave> getLeaveByCode(String code) {

        Optional<Leave> leave = leaveRepository.findLeaveByCode(code);
        if (leave.isPresent()) {
            leave.get().setTypeOfLeaves(StaticData.getLeaveType());
            return  leave;
        }

        return Optional.empty();
    }
}
