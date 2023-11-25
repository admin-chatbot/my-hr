package com.hr.company.reporitory;

import com.hr.company.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeaveRepository extends JpaRepository<Leave,Long> {

    public Optional<Leave> findLeaveByCode(String code);
}
