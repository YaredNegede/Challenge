package com.workmotion.challenge.employee;

import com.workmotion.challenge.employee.state.Event;
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
    public ResponseEntity<Void> save(@RequestBody EmployeeDTO employeeDTO){

        log.info(employeeDTO.toString());

        this.employeeService.save(employeeDTO);

        return ResponseEntity.accepted().build();

    }

    @PostMapping("/employee/{id}/{state}")
    public ResponseEntity<Void> next(@PathVariable("id")  long id, @PathVariable("state") Event event){

        log.info(event.toString());

        employeeService.next(id,event);

        return ResponseEntity.accepted().build();

    }

}
