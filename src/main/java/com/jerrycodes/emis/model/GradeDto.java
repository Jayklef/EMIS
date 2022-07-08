package com.jerrycodes.emis.model;

import com.jerrycodes.emis.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeDto {

    private String name;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;

}
