package com.workmotion.challenge.employee;

import com.workmotion.challenge.employee.state.State;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {

   @Id
   @GeneratedValue(strategy= GenerationType.AUTO)
   @Column(updatable = false, nullable = false)
   private long id;


   private State employeeState = State.ADDED;

   private String profilePic;

   private String firstName;

   private String gender;

   private String lastName;

   private String familyName;

   @ManyToMany
   @JoinTable(name = "employee_contract",
           joinColumns={@JoinColumn(name = "fk_employee")},
           inverseJoinColumns = {@JoinColumn(name = "fk_contract")}
   )
   private Set<ContractDetail> contractDetail;

}
