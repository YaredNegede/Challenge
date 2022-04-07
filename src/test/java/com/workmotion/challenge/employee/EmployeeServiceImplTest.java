package com.workmotion.challenge.employee;

import com.github.oxo42.stateless4j.StateMachine;
import com.github.oxo42.stateless4j.StateMachineConfig;
import com.workmotion.challenge.employee.state.Event;
import com.workmotion.challenge.employee.state.MachineFactory;
import com.workmotion.challenge.employee.state.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class EmployeeServiceImplTest {

    static EmployeeService employeeService;

    static EmployeeRepository employeeRepository;

    StateMachineConfig<State, Event> machineConfig = MachineFactory.build();

    StateMachine<State, Event> fsm;

    Employee employee;

    @BeforeEach
    public  void reset() {

        employeeRepository = mock(EmployeeRepository.class);

        employeeService = new EmployeeServiceImpl(employeeRepository);

        StateMachineConfig<State, Event> machineConfig = MachineFactory.build();

        fsm = new StateMachine<>(State.ADDED, machineConfig);

        employee = Employee.builder().employeeState(State.ADDED).build();

    }

    @Test
    void get() {

        Optional<Employee> employee = Optional.of(mock(Employee.class));

        when(employeeRepository.findById(any())).thenReturn(employee);

        Optional<EmployeeDTO> res = employeeService.get(1);

        assertThat(res.isPresent()).isTrue();

    }

    @Test
    void save() {

        EmployeeDTO employeeDTO = mock(EmployeeDTO.class);

        employeeService.save(employeeDTO);

        verify(employeeRepository,atMostOnce()).save(any());

    }

    /*
        @ParameterizedTest
        @EnumSource(value = Event.class, names =  {"BEGIN_CHECK", "APPROVE", "ACTIVATE"})
        void next( Event event) {

            when(employeeRepository.findById(1l)).thenReturn(Optional.of(employee));

            EmployeeServiceImpl service = (EmployeeServiceImpl) employeeService;

            service.next(1,event);

            employee.setEmployeeState(fsm.getState());

            verify(employeeRepository,atMostOnce()).save(any());

        }*/
    /*
        @ParameterizedTest
        @EnumSource(value = Event.class,names =  {"BEGIN_CHECK", "APPROVE","UNAPPROVE", "ACTIVATE"})
        void next2( Event event) {

            when(employeeRepository.findById(1l)).thenReturn(Optional.of(employee));

            EmployeeServiceImpl service = (EmployeeServiceImpl) employeeService;

            service.next(1,event);

            verify(employeeRepository,atMostOnce()).save(any());

            employee.setEmployeeState(fsm.getState());

        }*/
    /*

        @ParameterizedTest
        @EnumSource(value = Event.class,names =  {"BEGIN_CHECK", "APPROVE","UNAPPROVE", "ACTIVATE"})
        void machineFacotryTest( Event event) {

            fsm.fire(event);

        }

        @ParameterizedTest
        @EnumSource(value = Event.class,names =  {"BEGIN_CHECK", "APPROVE", "ACTIVATE"})
        void machineFacotryTest2( Event event) {

            fsm.fire(event);

        }
    */

    @Test
    void valideState() {

        fsm.fire(Event.BEGIN_CHECK);

        assertThat(fsm.getState()).isEqualTo(State.IN_CHECK);

        fsm.fire(Event.APPROVE);

        assertThat(fsm.getState()).isEqualTo(State.APPROVED);

        fsm.fire(Event.ACTIVATE);

        assertThat(fsm.getState()).isEqualTo(State.ACTIVATE);



    }

    @Test
    public void illegelState(){

        StateMachineConfig<State, Event> machineConfig = MachineFactory.build();

        var fsm = new StateMachine<>(State.ADDED, machineConfig);

         assertThrows(IllegalStateException.class, () -> {

            fsm.fire(Event.UNAPPROVE);

            fsm.fire(Event.APPROVE);

            fsm.fire(Event.BEGIN_CHECK);

        });

    }

}