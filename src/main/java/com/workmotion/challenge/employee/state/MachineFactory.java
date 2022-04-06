package com.workmotion.challenge.employee.state;

import com.github.oxo42.stateless4j.StateMachineConfig;

public class MachineFactory {

   public static  StateMachineConfig<State, Event> build(){

        StateMachineConfig<State, Event> config = new StateMachineConfig<>();

        config.configure(State.ADDED)
                .permit(Event.BEGIN_CHECK,State.IN_CHECK);

        config.configure(State.IN_CHECK)
                .permit(Event.APPROVE,State.APPROVED);

        config.configure(State.APPROVED)
                .permit(Event.ACTIVATE,State.ACTIVATE);

        config.configure(State.APPROVED)
                .permit(Event.UNAPPROVE,State.IN_CHECK);

        return config;

    }
}
