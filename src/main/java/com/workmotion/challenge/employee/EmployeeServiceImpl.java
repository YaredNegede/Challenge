package com.workmotion.challenge.employee;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Optional<EmployeeDTO> get(long id) {
        return Optional.empty();
    }

    @Override
    public void save(EmployeeDTO employeeDTO) {


    }

    @Override
    public void next(long id, EmployeeStateEnum state) {

    }

}
