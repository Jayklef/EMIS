package com.jerrycodes.emis.model;

import com.jerrycodes.emis.entity.Department;
import com.jerrycodes.emis.entity.Grade;
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
    private Department department;
    private Grade grade;
}
