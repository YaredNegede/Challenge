package com.workmotion.challenge.employee;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ContractDetail {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private long id;

    private String name;

    private String type;

    @ManyToMany(mappedBy = "contractDetail")
    private List<Employee> employees;

}
