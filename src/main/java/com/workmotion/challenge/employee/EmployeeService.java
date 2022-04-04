package com.workmotion.challenge.employee;

import com.workmotion.challenge.employee.state.Event;
import com.workmotion.challenge.employee.state.State;

import java.util.Optional;


public interface EmployeeService {

    Optional<EmployeeDTO> get(long id);

    void save(EmployeeDTO employeeDTO);

    void next(long id, Event event);
}
