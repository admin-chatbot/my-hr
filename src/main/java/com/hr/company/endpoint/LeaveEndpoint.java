package com.hr.company.endpoint;


import com.hr.company.model.Employee;
import com.hr.company.model.Leave;
import com.hr.company.model.LeaveDetails;
import com.hr.company.service.LeaveDetailsService;
import com.hr.company.service.LeaveService;
import com.hr.company.utils.LeaveBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/v1/leave/",produces = MediaType.APPLICATION_JSON_VALUE)
public class LeaveEndpoint {

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private LeaveDetailsService leaveDetailsService;

    @GetMapping("{empid}/")
    public ResponseEntity getInfo(@PathVariable(name="empid") String code) {
        try {
           Optional<Leave> leave =  leaveService.getLeaveByCode(code);

           if (leave.isPresent()) {
               List<LeaveDetails> leaveDetails = leaveDetailsService.getRepository().findLeaveDetailsByEmpCode(leave.get().getCode());
               leave.get().setLeaveDetails(leaveDetails);
           }

           if (leave.isPresent())
               return ResponseEntity.ok(leave.get());

           return ResponseEntity.ok("No data found.");
        }catch (Exception exception) {
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }
    }

    @PostMapping(path = "load/",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> load(@RequestParam("file") MultipartFile file) {
        try{
            Map<String, String> map = new HashMap<>();
            LeaveBuilder leaveBuilder = new LeaveBuilder();

            // Populate the map with file details
            try (BufferedReader br
                         = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;

                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    leaveBuilder.addLeave(line);
                }
            }
            List<Leave> leaveList = leaveBuilder.getLeaves();
            for (Leave leave: leaveList) {
               Leave result =  leaveService.getRepository().save(leave);
                for (LeaveDetails details : leave.getLeaveDetails()) {
                    details.setLeaveId(result.getId());
                    LeaveDetails apply = details;
                    leaveDetailsService.getRepository().save(apply);
                }
            }
            // File upload is successful
            map.put("message", "Leave added successfully. Count :"+ leaveList.size());
            leaveBuilder.clear();
            return ResponseEntity.ok(map);
        }catch (Exception exception) {
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }
    }




}
