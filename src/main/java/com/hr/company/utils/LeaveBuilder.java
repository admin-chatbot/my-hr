package com.hr.company.utils;

import com.hr.company.model.Leave;
import com.hr.company.model.LeaveDetails;
import org.apache.commons.lang3.StringUtils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class LeaveBuilder {

    private Map<String, Leave> leaves;

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public LeaveBuilder() {
        init();
    }

    private void init(){
        leaves = new HashMap<>();
    }

    public List<Leave> getLeaves(){
       return new ArrayList<>(this.leaves.values());
    }

    public void clear(){
        this.leaves.clear();
        init();
    }

    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public void addLeave(String leaveDetails) throws ParseException {
        if (!StringUtils.isBlank(leaveDetails)){
            String[] values = StringUtils.split(leaveDetails,"|");

            LeaveDetails leaveDetails1
                    = LeaveDetails.builder()
                        .empCode(values[0])
                        .type(values[1])
                        .startDate(new Date( dateFormat.parse(values[2]).getTime()))
                        .endDate(new Date( dateFormat.parse(values[3]).getTime()))
                    .build();

            addLeave(leaveDetails1,values[4]);
        }
    }

    public void addLeave(LeaveDetails leaveDetails,String year) {
        if (this.leaves==null)
            init();

        assert leaveDetails != null;

        String empCode = leaveDetails.getEmpCode();
        long days = getDifferenceDays(leaveDetails.getStartDate(),leaveDetails.getEndDate());

        if (this.leaves.containsKey(empCode)) {
            Leave leave = this.leaves.get(empCode);
            leave.setAvailedLeave((int) (leave.getAvailedLeave()+days));
            if(leave.getLeaveDetails()==null)
                leave.setLeaveDetails(new ArrayList<>());

            leave.getLeaveDetails().add(leaveDetails);

        } else {
            Leave leave = new Leave();
            leave.setCode(leaveDetails.getEmpCode());
            leave.setLeaveYear(year);
            leave.setTotalLeave(StaticData.getTotalLeave());
            leave.setAvailedLeave((int) days);
            if(leave.getLeaveDetails()==null)
                leave.setLeaveDetails(new ArrayList<>());

            leave.getLeaveDetails().add(leaveDetails);
            this.leaves.put(empCode,leave);
        }


    }




}
