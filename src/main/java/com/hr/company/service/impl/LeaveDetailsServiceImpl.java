package com.hr.company.service.impl;

import com.hr.company.reporitory.LeaveDetailsRepository;
import com.hr.company.service.LeaveDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LeaveDetailsServiceImpl implements LeaveDetailsService {

    @Autowired
    private LeaveDetailsRepository leaveDetailsRepository;

    @Override
    public LeaveDetailsRepository getRepository() {
        return leaveDetailsRepository;
    }
}
