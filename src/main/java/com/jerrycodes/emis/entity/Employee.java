package com.jerrycodes.emis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Please enter employee firstname")
    private String firstname;

    @NotBlank(message = "Please enter employee lastname")
    private String lastname;

    @Past(message = "Birthdate cannot be present or future date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;
    private String gender;

    @Email(message = "Please enter a valid email address")
    private String email;
    private String phonenumber;
    private String address;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @OneToMany(mappedBy = "employee")
    private Set<Client> clients;

    @ManyToOne
    @JoinColumn(name = "grade_id", nullable = false)
    private Grade grade;
}
