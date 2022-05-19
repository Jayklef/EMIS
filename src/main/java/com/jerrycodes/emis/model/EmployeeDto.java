package com.jerrycodes.emis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private String firstname;
    private String lastname;
    private Date birthdate;
    private String gender;
    private String email;
    private String phonenumber;
    private String address;
}
