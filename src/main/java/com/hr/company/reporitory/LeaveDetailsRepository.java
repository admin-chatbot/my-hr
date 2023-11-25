package com.hr.company.reporitory;

import com.hr.company.model.LeaveDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveDetailsRepository extends JpaRepository<LeaveDetails,Long> {

    public List<LeaveDetails> findLeaveDetailsByEmpCode(String empCode);
}
