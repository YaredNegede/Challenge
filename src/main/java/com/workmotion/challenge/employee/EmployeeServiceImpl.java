package com.workmotion.challenge.employee;

import com.github.oxo42.stateless4j.StateMachine;
import com.github.oxo42.stateless4j.StateMachineConfig;
import com.workmotion.challenge.employee.state.State;
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

        var fsm = new StateMachine<>(State.ADDED, build(employeeId));

        fsm.fire(event);

    }


    private StateMachineConfig<State, Event> build(long employeeId){

        StateMachineConfig<State, Event> phoneCallConfig = new StateMachineConfig<>();

        this.employeeRepository.findById(employeeId).ifPresent(employee -> {

            phoneCallConfig.configure(State.ADDED)
                .permit(Event.BEGIN_CHECK,State.IN_CHECK,()->{
                    employee.setEmployeeState(State.IN_CHECK);
                    this.employeeRepository.save(employee);
                }).permit(Event.APPROVE,State.APPROVED,() -> {
                        employee.setEmployeeState(State.IN_CHECK);
                        this.employeeRepository.save(employee);
                }).permit(Event.UNAPPROVE,State.IN_CHECK,() -> {
                        employee.setEmployeeState(State.IN_CHECK);
                        this.employeeRepository.save(employee);
                }).permit(Event.ACTIVATE,State.ACTIVATE,() -> {
                        employee.setEmployeeState(State.IN_CHECK);
                        this.employeeRepository.save(employee);
                });

        });

        return phoneCallConfig;

    }

}
