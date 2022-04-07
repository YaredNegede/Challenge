package com.workmotion.challenge.employee.integration;

import com.workmotion.challenge.employee.*;
import com.workmotion.challenge.employee.state.Event;
import com.workmotion.challenge.employee.state.State;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class EmployeeControllerTest {

    static EmployeeController  employeeController;
    static EmployeeService employeeService;
    static EmployeeRepository employeeRepository;

    @BeforeAll
    public static void setUp(){
        employeeRepository = mock(EmployeeRepository.class);
        employeeService = new EmployeeServiceImpl(employeeRepository);
        employeeController = new EmployeeController(employeeService);
    }

    @Test
    @Description("test is the the i can read the employee")
    void get(){

        Employee emDto= mock(Employee.class);

        when(employeeRepository.findById(1l)).thenReturn(Optional.of(emDto));

        ResponseEntity<EmployeeDTO> employee = employeeController.getEmployee(1);

        assertThat(employee).isNotNull();
        assertThat(employee.getBody()).isNotNull();
        assertThat(employee.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @Description("test is the employee is saved")
    void testIfItsavessave(){

        EmployeeDTO emDto= mock(EmployeeDTO.class);
        Employee en= mock(Employee.class);


        when(employeeRepository.save(any())).thenReturn(en);

        ResponseEntity<Void> employee = employeeController.save(emDto);

        assertThat(employee).isNotNull();
        assertThat(employee.getBody()).isNull();
        assertThat(employee.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    @Description("Test state transitions, there should not be any exception thrown, ot it should just pass")
    void state(){

        Employee en= mock(Employee.class);
        en.setEmployeeState(State.ADDED);

        Optional<Employee> optionalEmployee = Optional.of(en);

        when(employeeRepository.findById(1l)).thenReturn(optionalEmployee);

        when(en.getEmployeeState()).thenReturn(State.ADDED);

        employeeController.next(1, Event.BEGIN_CHECK);

        when(employeeRepository.findById(any())).thenReturn(optionalEmployee);
        when(en.getEmployeeState()).thenReturn(State.IN_CHECK);

        employeeController.next(1, Event.APPROVE);

        when(en.getEmployeeState()).thenReturn(State.APPROVED);

        employeeController.next(1, Event.UNAPPROVE);

        when(en.getEmployeeState()).thenReturn(State.IN_CHECK);

        employeeController.next(1, Event.APPROVE);

        when(en.getEmployeeState()).thenReturn(State.APPROVED);

        employeeController.next(1, Event.ACTIVATE);

    }

}
