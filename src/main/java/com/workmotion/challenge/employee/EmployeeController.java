package com.workmotion.challenge.employee;

import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log
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

        log.info(employeeDTO.toString());

        this.employeeService.save(employeeDTO);

        return ResponseEntity.accepted().build();

    }

    @PostMapping("/employee/{id}/{state}")
    public ResponseEntity<Void> next(@PathVariable("id")  long id, @PathVariable("state") EmployeeStateEnum state){

        log.info(state.toString());

        employeeService.next(id,state);

        return ResponseEntity.accepted().build();

    }

}
