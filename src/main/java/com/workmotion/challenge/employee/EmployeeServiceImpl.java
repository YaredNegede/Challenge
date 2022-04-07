package com.workmotion.challenge.employee;

import com.github.oxo42.stateless4j.StateMachine;
import com.workmotion.challenge.employee.state.MachineFactory;
import com.workmotion.challenge.employee.state.Event;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Optional<EmployeeDTO> get(long id) {

        Optional<Employee> em = employeeRepository.findById(id);

        Employee employee = em.orElseThrow();

        return Optional.ofNullable(
                EmployeeDTO.builder()
                .familyName(employee.getFamilyName())
                .gender(employee.getGender())
                .lastName(employee.getLastName())
                .profilePic(employee.getProfilePic())
                .build());

    }

    @Override
    public void save(EmployeeDTO employeeDTO) {

        Employee employee = Employee.builder().familyName(employeeDTO.familyName)
                .gender(employeeDTO.gender)
                .lastName(employeeDTO.lastName)
                .profilePic(employeeDTO.profilePic)
                .build();

        this.employeeRepository.save(employee);

    }

    @Override
    public void next(long employeeId, Event event) {

        this.employeeRepository.findById(employeeId).ifPresent(employee -> {

            var fsm = new StateMachine<>(employee.getEmployeeState(), MachineFactory.build());

            fsm.fire(event);

            if(!employee.getEmployeeState().equals(fsm.getState())) {

                employee.setEmployeeState(fsm.getState());

                employeeRepository.save(employee);

            }

        });

    }

}
