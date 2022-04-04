package com.workmotion.challenge.employee;

import org.springframework.stereotype.Service;

import java.util.Optional;


public interface EmployeeService {

    Optional<EmployeeDTO> get(long id);

    void save(EmployeeDTO employeeDTO);

    void next(long id, EmployeeStateEnum state);
}
