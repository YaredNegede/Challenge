package com.workmotion.challenge;

import com.github.oxo42.stateless4j.StateMachineConfig;
import com.workmotion.challenge.employee.state.Event;
import com.workmotion.challenge.employee.state.State;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}

}
