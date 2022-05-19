package com.jerrycodes.emis.controller;

import com.jerrycodes.emis.entity.Client;
import com.jerrycodes.emis.entity.Employee;
import com.jerrycodes.emis.model.EmployeeDto;
import com.jerrycodes.emis.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/list_emp")
    public ResponseEntity<List<Employee>> getEmployees(){
        List<Employee> employees = employeeService.findAll();
       return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/all/{birthdate}")
    public ArrayList<Employee> employeesByFirstname(@PathVariable("birthdate") Date birthdate){
        ArrayList<Employee> employees = employeeService.findByBirthdate(birthdate);
        return new ArrayList<>(employees);
    }

    @GetMapping("/{gender}")
    public ResponseEntity<List<Employee>> findByGender(@PathVariable String gender, Pageable pageable){
        List<Employee> employees = employeeService.findByGender(gender, pageable);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> addEmployee(@ModelAttribute("employee") EmployeeDto employeeDto){
        Employee employee = employeeService.addEmployee(employeeDto);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findEmployee(@PathVariable("id") Long id){
        Employee employee = employeeService.findEmplyeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, Employee employee){
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @GetMapping("/{pageandsortemployees}/{pagenumber}/{pagesize}")
    public Page<Employee> employeePagination(@PathVariable("pageNumber") Integer pageNumber,
                                                @PathVariable("pagesize") Integer pageSize){
       /* Pageable pageable = PageRequest.of(pageNumber, pageSize); */
        return employeeService.getEmployeePagination(pageNumber, pageSize);
    }

    @GetMapping("/{pageandsortemployees}/{pagenumber}/{pagesize}/{sortproperty}")
    public Page<Employee> employeePagination(@PathVariable Integer pageNumber,
                                             @PathVariable Integer pageSize,
                                             @PathVariable String sortProperty ){
        return employeeService.getEmployeeAndPagination(pageNumber, pageSize, sortProperty);
    }

    @GetMapping("/{id}/clients")
    public ResponseEntity<List<Client>> getClientsByEmployee(@PathVariable("id") Long id,
                                                             Client client){
        List<Client> clientList = employeeService.findClientsByEmployeeId(id, client);
        return new ResponseEntity<>(List.copyOf(clientList), HttpStatus.OK);
    }

    public Long numberOfClientsByEmployee(@PathVariable("id") Long id){
        return employeeService.findNumberByEmployeeId(id);
      //  return new ArrayList<>(List.copyOf(client.getEmployee().getClients()));
    }


}
