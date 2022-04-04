package com.workmotion.challenge.employee;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable("id") long id){

        return ResponseEntity.of(this.employeeService.get(id));

    }

    @PostMapping("/employee")
    public ResponseEntity<Void> save(EmployeeDTO employeeDTO){

        this.employeeService.save(employeeDTO);

        return ResponseEntity.accepted().build();

    }

}
