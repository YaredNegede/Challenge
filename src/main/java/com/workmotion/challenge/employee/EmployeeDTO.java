package com.workmotion.challenge.employee;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@ToString
public class EmployeeDTO {

    @NotBlank
    @Size(min = 0, max = 20)
    private String profilePic;

    @NotBlank
    @Size(min = 0, max = 20)
    private String firstName;

    @NotBlank
    @Size(min = 0, max = 20)
    private String gender;

    @NotBlank
    @Size(min = 0, max = 20)
    private String lastName;

    @NotBlank
    @Size(min = 0, max = 20)
    private String familyName;

}
