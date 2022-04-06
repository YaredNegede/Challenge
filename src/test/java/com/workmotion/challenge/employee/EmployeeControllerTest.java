package com.workmotion.challenge.employee;

import com.workmotion.challenge.employee.state.Event;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    static EmployeeService employeeService;

    static EmployeeController employeeController;

    @BeforeAll
    public static void setup() {

        employeeService = mock(EmployeeService.class);

        employeeController = new EmployeeController(employeeService);

    }

    @Test
    public void getEmployee() {

        when(employeeService.get(1)).thenReturn(Optional.of(mock(EmployeeDTO.class)));

        employeeController = new EmployeeController(employeeService);

        ResponseEntity<EmployeeDTO> employee =employeeController.getEmployee(1);

        verify(employeeService,atMostOnce()).get(1);

    }

    @Test
    void save() {

        EmployeeDTO employee = mock(EmployeeDTO.class);

        employeeController.save(employee);

        verify(employeeService,atMostOnce()).save(employee);

    }

    @Test
    void next() {

        long id = 1;

        Event event = Event.ACTIVATE;

        employeeController.next(id,event);

    }
}