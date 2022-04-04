package com.workmotion.challenge.employee;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@ToString
public class EmployeeDTO {

    @NotBlank
    @Size(min = 0, max = 20)
    public String profilePic;

    @NotBlank
    @Size(min = 0, max = 20)
    public String firstName;

    @NotBlank
    @Size(min = 0, max = 20)
    public String gender;

    @NotBlank
    @Size(min = 0, max = 20)
    public String lastName;

    @NotBlank
    @Size(min = 0, max = 20)
    public String familyName;

}
