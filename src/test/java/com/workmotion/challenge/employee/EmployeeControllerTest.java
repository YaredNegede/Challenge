package com.workmotion.challenge.employee;

import com.workmotion.challenge.employee.state.Event;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    static EmployeeService employeeService;

    static EmployeeController employeeController;

    @BeforeAll
    public static void setup() {

        employeeController = new EmployeeController(employeeService);

    }

    @BeforeEach
    public  void reset() {

        employeeService = mock(EmployeeService.class);

    }

    @Test
    public void checkIfTheEmployeeCanBeRead() {

        when(employeeService.get(1)).thenReturn(Optional.of(mock(EmployeeDTO.class)));

        employeeController = new EmployeeController(employeeService);

        ResponseEntity<EmployeeDTO> employeeDTOResponseEntity =employeeController.getEmployee(1);

        verify(employeeService,atMostOnce()).get(1);

        assertThat(employeeDTOResponseEntity).isNotNull();

        assertThat(employeeDTOResponseEntity.getBody()).isNotNull();

        assertThat(employeeDTOResponseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);

    }

    @Test
    public void checkIfTheNoneExistingEmployeeRead() {

        when(employeeService.get(1)).thenReturn(Optional.empty());

        employeeController = new EmployeeController(employeeService);

        ResponseEntity<EmployeeDTO> employeeDTOResponseEntity =employeeController.getEmployee(1);

        verify(employeeService,atMostOnce()).get(1);

        assertThat(employeeDTOResponseEntity).isNotNull();

        assertThat(employeeDTOResponseEntity.getBody()).isNull();

        assertThat(employeeDTOResponseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.NOT_FOUND);

    }

    @Test
    void checkIfTheEmployeeCanVeSaved() {

        EmployeeDTO employee = mock(EmployeeDTO.class);

        ResponseEntity<Void> responce = employeeController.save(employee);

        verify(employeeService,atMostOnce()).save(employee);

        assertThat(responce).isNotNull();

        assertThat(responce.getBody()).isNull();

        assertThat(responce.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);

    }

    @Test
    void next() {

        long id = 1;

        Event event = Event.ACTIVATE;

        ResponseEntity<Void> responce = employeeController.next(id,event);

        assertThat(responce).isNotNull();

        assertThat(responce.getBody()).isNull();

        assertThat(responce.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);

    }

}