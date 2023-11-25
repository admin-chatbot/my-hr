package com.hr.company.service;

import com.hr.company.reporitory.LeaveDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public interface LeaveDetailsService {

    public LeaveDetailsRepository getRepository();
}
