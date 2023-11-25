package com.hr.company.utils;

import java.util.HashMap;
import java.util.Map;

public class StaticData {

    public static Map<String,Integer> getLeaveType(){
        HashMap<String,Integer> leaveMap = new HashMap<>();
        leaveMap.put("SICK-LEAVE",12);
        leaveMap.put("ANNUAL-LEAVE",22);
        return leaveMap;
    }

    public static int getTotalLeave() {
       return StaticData.getLeaveType().values().stream().mapToInt(Integer::intValue).sum();
    }


}
