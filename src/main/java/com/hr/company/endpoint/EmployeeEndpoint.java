package com.hr.company.endpoint;

import com.hr.company.model.Employee;
import com.hr.company.model.Leave;
import com.hr.company.service.EmployeeService;
import com.hr.company.service.LeaveService;
import jakarta.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/v1/employee/",produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeEndpoint {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LeaveService leaveService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveEmployee(@RequestBody @Valid Employee employee) {
        try {
            Employee employee1 = employeeService.save(employee);
            Leave leave = leaveService.addDefaultLeaves(employee1.getCode());
            return ResponseEntity.ok(employee1);
        }catch (Exception exception) {
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }
    }



    @PostMapping(path = "load/",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> load(@RequestParam("file") MultipartFile file) {
        try{
            Map<String, String> map = new HashMap<>();

            // Populate the map with file details

            ArrayList<Employee> employees = new ArrayList<>();
            try (BufferedReader br
                         = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;

                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    employees.add(buildEmployee(line));
                }
            }
           List<Employee> employeeList = employeeService.save(employees);

            // File upload is successful
            map.put("message", "Employees added successfully. Count :"+ employeeList.size());
            employees.clear();
            return ResponseEntity.ok(map);
        }catch (Exception exception) {
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }
    }

    private Employee buildEmployee(String rawData) {
        if (!StringUtils.isBlank(rawData)) {
           String[] values = StringUtils.split(rawData,"|");

           return Employee.builder()
                       .code(values[0])
                       .name(values[1])
                       .address(values[2])
                   .build();
        }
        return null;
    }

}
