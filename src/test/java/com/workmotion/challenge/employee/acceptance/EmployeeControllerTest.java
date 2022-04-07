package com.workmotion.challenge.employee.acceptance;

import com.workmotion.challenge.employee.EmployeeController;
import com.workmotion.challenge.employee.EmployeeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    EmployeeController  employeeController;

    @Test
    @Description("test is the the i can read the employee")
    void get(){

    }

    @Test
    @Description("test is the employee is saved")
    void testIfItsavessave(){

    }

    @Test
    @Description("test is the onboarding works")
    void state(){

    }

}
