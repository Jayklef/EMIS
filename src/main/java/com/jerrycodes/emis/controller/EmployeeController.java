package com.jerrycodes.emis.controller;

import com.jerrycodes.emis.entity.Client;
import com.jerrycodes.emis.entity.Employee;
import com.jerrycodes.emis.model.EmployeeDto;
import com.jerrycodes.emis.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    private EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping("/list_emp")
    public ResponseEntity<List<Employee>> getEmployees(){
        List<Employee> employees = employeeService.findAll();
        LOGGER.info("Inside getEmployees of Employee Controller");
       return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/all/{birthdate}")
    public ArrayList<Employee> employeeseByBirthdate(@PathVariable("birthdate") Date birthdate){
        ArrayList<Employee> employees = employeeService.findByBirthdate(birthdate);
        LOGGER.info("Inside getEmployeesByBirthdate of Employee Controller");
        return new ArrayList<>(employees);
    }

    @GetMapping("/{gender}")
    public ResponseEntity<List<Employee>> findByGender(@PathVariable String gender, Pageable pageable){
        List<Employee> employees = employeeService.findByGender(gender, pageable);
        LOGGER.info("Inside findEmployeeByGender Pageable of Employee Controller");
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/genders")
    public ResponseEntity<List<Employee>> getByGender(@PathVariable String gender){
        List<Employee> employees = employeeService.findAllByGender(gender);
        LOGGER.info("Inside getEmployeeByGender of Employee Controller");
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody EmployeeDto employeeDto){
        Employee newEmployee = employeeService.addEmployee(employeeDto);
        LOGGER.info("Inside addEmployee of Employee Controller");
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findEmployee(@PathVariable("id") Long id){
        Employee employee = employeeService.findEmployeeById(id);
        LOGGER.info("Inside findEmployeeById of Employee Controller");
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id,
                                                   @RequestBody Employee employee){
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        LOGGER.info("Inside updateEmployee of Employee Controller");
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @GetMapping("/pageAndSortEmployees/{pageNumber}/{pageSize}")
    public Page<Employee> employeePagination(@PathVariable("pageNumber") Integer pageNumber,
                                                @PathVariable("pageSize") Integer pageSize){
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
        LOGGER.info("Inside getByClientsByEmployee of Employee Controller");
        return new ResponseEntity<>(List.copyOf(clientList), HttpStatus.OK);
    }

    @GetMapping("/{id}/clients/number")
    public Long numberOfClientsByEmployee(@PathVariable("id") Long id){
        LOGGER.info("Inside numberOfClientsByEmployee of Employee Controller");
        return employeeService.findNumberByEmployeeId(id);
      //  return new ArrayList<>(List.copyOf(client.getEmployee().getClients()));
    }
    @GetMapping("")
    public BigDecimal getTotalAmountByEmployee(@PathVariable("id") Long id, Client client){
        LOGGER.info("Inside getTotalAmountByEmployee of Employee Controller");
        BigDecimal total = employeeService.findTotalAmountByEmployee(id, client);
        return total;
    }

    @GetMapping("/{id}/clients/balances")
    public Set<Map<String, BigDecimal>> getAllClientsAndBalancesByEmployee(@PathVariable("id") Long id){
        Set<Map<String, BigDecimal>> employee = employeeService.findClientBalances(id);
        LOGGER.info("Inside getAllClientsAndBalancesByEmployee of Employee Controller");
        return employee;
    }

}
