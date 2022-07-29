package com.jerrycodes.emis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Please enter employee grade")
    private String name;
    private BigDecimal minSalary;

    private BigDecimal maxSalary;

    @OneToMany(mappedBy = "grade")
    private Set<Employee> employee;
}
