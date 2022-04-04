package com.workmotion.challenge.employee;

import com.workmotion.challenge.employee.state.State;
import com.workmotion.challenge.employee.state.Event;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.statemachine.support.StateMachineInterceptor;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private StateMachineFactory<State, Event> factory;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, StateMachineFactory<State, Event> factory) {
        this.employeeRepository = employeeRepository;
        this.factory = factory;
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

        build(employeeId);

    }

    StateMachine<State, Event> build(long employeeId){

        Employee employee = employeeRepository.getById(employeeId);

        StateMachine<State, Event> machine = this.factory.getStateMachine();

        machine.stop();

        machine.getStateMachineAccessor()
                .doWithAllRegions(stateEventStateMachineAccess ->
                        {
                            stateEventStateMachineAccess.addStateMachineInterceptor(new StateMachineInterceptorAdapter<>() {
                                @Override
                                public void preStateChange(org.springframework.statemachine.state.State<State, Event> state,
                                                           Message<Event> message, Transition<State,
                                                           Event> transition,
                                                           StateMachine<State, Event> stateMachine) {

                                    Optional.of(message).ifPresent(employee->{

                                        employeeRepository.findById(employeeId).ifPresent(emp -> {

                                            emp.setEmployeeState(state.getId());

                                            employeeRepository.save(emp);

                                        });

                                    });
                                }
                            });
                        stateEventStateMachineAccess.resetStateMachine(new DefaultStateMachineContext<>(employee.getEmployeeState(), null, null, null));

                    }
                );
        machine.start();

        return machine;
    }

}
