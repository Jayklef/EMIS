package com.jerrycodes.emis.service;

import com.jerrycodes.emis.entity.Client;
import com.jerrycodes.emis.entity.Employee;
import com.jerrycodes.emis.model.EmployeeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee addEmployee(EmployeeDto employeeDto);

    List<Employee> findByGender(String gender, Pageable pageable);

    Employee updateEmployee(Long id, Employee employee);

    Page<Employee> getEmployeePagination(Integer pageNumber, Integer pageSize);

    Page<Employee> getEmployeeAndPagination(Integer pageNumber, Integer pageSize, String sortProperty);

    Employee findEmplyeeById(Long id);

    ArrayList<Employee> findByBirthdate(Date birthdate);

    List<Client> findClientsByEmployeeId(Long id, Client client);

    long findNumberByEmployeeId(Long id);
}
