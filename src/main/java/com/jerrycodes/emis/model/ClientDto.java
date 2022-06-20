package com.jerrycodes.emis.model;

import com.jerrycodes.emis.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private String name;
    private BigDecimal accountBalance;
}
