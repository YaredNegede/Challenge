package com.workmotion.challenge.employee.state;

import lombok.extern.java.Log;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;

import java.util.EnumSet;

@Log
@Configuration
@EnableStateMachineFactory
public class Config extends EnumStateMachineConfigurerAdapter<State,Event> {

    @Override
    public void configure(StateMachineStateConfigurer<State, Event> states) throws Exception {
        states.withStates()
                .initial(State.ADDED)
                .states(EnumSet.allOf(State.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<State, Event> transition) throws Exception {
        transition.withExternal()
                 .source(State.ADDED).target(State.IN_CHECK).event(Event.BEGIN_CHECK)
                 .source(State.IN_CHECK).target(State.APPROVED).event(Event.APPROVE)
                 .source(State.APPROVED).target(State.IN_CHECK).event(Event.UNAPPROVE)
                 .source(State.APPROVED).target(State.ACTIVATE).event(Event.ACTIVATE);
    }

}
