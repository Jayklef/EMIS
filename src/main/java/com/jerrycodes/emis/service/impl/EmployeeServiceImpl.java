package com.jerrycodes.emis.service.impl;

import com.jerrycodes.emis.entity.Client;
import com.jerrycodes.emis.entity.Employee;
import com.jerrycodes.emis.entity.Grade;
import com.jerrycodes.emis.exception.EmployeeNotFoundException;
import com.jerrycodes.emis.model.EmployeeDto;
import com.jerrycodes.emis.repository.EmployeeRepository;
import com.jerrycodes.emis.service.EmployeeService;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    @Override
    public Employee addEmployee(EmployeeDto employeeDto) {

        Employee employee = Employee
                .builder()
                .firstname(employeeDto.getFirstname())
                .lastname(employeeDto.getLastname())
                .birthdate(employeeDto.getBirthdate())
                .gender(employeeDto.getGender())
                .email(employeeDto.getEmail())
                .phonenumber(employeeDto.getPhonenumber())
                .address(employeeDto.getAddress())
                .department(employeeDto.getDepartment())
                .grade(employeeDto.getGrade())
                .clients(Set.of())
                .build();

        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findByGender(String gender, Pageable pageable) {
        if (gender.isEmpty()){
           throw new IllegalIdentifierException("please enter employee gender");
        }
        /*Pageable genderPage = employeeRepository.findByGender(gender, pageable);*/
        return employeeRepository.findByGender(gender, pageable)
                .stream()
                .sorted(Comparator.comparing(Employee::getGender))
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> findAllByGender(String gender){
        return employeeRepository.findAllByGender(gender)
                .stream()
                .sorted(Comparator.comparing(Employee::getGender))
                .collect(Collectors.toList());
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee employeeInDb = employeeRepository.findById(id).get();

        if (Objects.nonNull(employee.getFirstname()) &&
        !"".equalsIgnoreCase(employee.getFirstname())){
            employeeInDb.setFirstname(employeeInDb.getFirstname());
        }

        if (Objects.nonNull(employee.getLastname()) &&
        !"".equalsIgnoreCase(employee.getLastname())){
            employeeInDb.setLastname(employee.getLastname());
        }

        if (Objects.nonNull(employee.getGender())&&
        !"".equalsIgnoreCase(employee.getGender())){
            employeeInDb.setGender(employee.getGender());
        }

        if (Objects.nonNull(employee.getBirthdate()) &&
        !"".equalsIgnoreCase(employee.getBirthdate().toString())){
            employeeInDb.setBirthdate(employee.getBirthdate());
        }

        if (Objects.nonNull(employee.getEmail()) &&
        !"".equalsIgnoreCase(employee.getEmail())){
            employeeInDb.setEmail(employee.getEmail());
        }

        if (Objects.nonNull(employee.getPhonenumber())&&
        !"".equalsIgnoreCase(employee.getPhonenumber())){
            employeeInDb.setPhonenumber(employee.getPhonenumber());
        }

        if (Objects.nonNull(employee.getAddress()) &&
        !"".equalsIgnoreCase(employee.getAddress())){
            employeeInDb.setAddress(employee.getAddress());
        }

            return employeeInDb;
        }


    @Override
    public Page<Employee> getEmployeePagination(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Page<Employee> getEmployeeAndPagination(Integer pageNumber, Integer pageSize, String sortProperty) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.valueOf(sortProperty));
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Employee findEmployeeById(Long id) {

        Optional<Employee> employee = employeeRepository.findById(id);
        if (!employee.isPresent() || employee == null){
            throw new RuntimeException("employee with id is not found" + id);
        }

        return employeeRepository.findById(id).get();
    }

    @Override
    public ArrayList<Employee> findByBirthdate(Date birthdate) {
        if (birthdate == null){
            throw new IllegalIdentifierException("please enter birthdate");
        }
        return employeeRepository.findAllByBirthdate(birthdate);
    }

    @Override
    public List<Client> findClientsByEmployeeId(Long id, Client client) {

        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isEmpty() || employee == null){
            throw new EmployeeNotFoundException("Employee with id not found" + id);
        }
        return employee.get().getClients()
                .stream()
                .sorted(Comparator.comparing(Client::getAccountBalance))
                .collect(Collectors.toList());
    }

    @Override
    public long findNumberByEmployeeId(Long id) {

        Optional<Employee> employee =employeeRepository.findById(id);

        if (employee.isEmpty() || employee == null){
            throw new RuntimeException();
        }
         /* return new ArrayList<>(List.copyOf(employee
                 .get()
                 .getClients())
                 .stream()
                 .count();
      //  return List.copyOf(employee.get().getClients()).stream().collect(Collectors.toList());
       /* final BigDecimal totalSum = employee.get().getClients()
                .stream().map(Client::getAccountBalance)
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO); */

      return employee.get().getClients().size();
    }

    @Override
    public BigDecimal findTotalAmountByEmployee(Long id, Client client) {

        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isEmpty() || employee == null){
            throw new EmployeeNotFoundException("Employee with Id not found");
        }
          /*return new ArrayList<>(List.copyOf(employee
                 .get()
                 .getClients())
                 .stream()
                 .count(); */
       // return List.copyOf(employee.get().getClients()).stream().collect(Collectors.toList());
        final BigDecimal totalSum = employee.get().getClients()
                .stream().map(Client::getAccountBalance)
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        return totalSum;

    }

    @Override
    public Set<Map<String, BigDecimal>> findClientBalances(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("employee with id not found " + id);
        }

        return employee.get().getClients()
                .stream()
                .map(client -> Map.of(client.getName(), client.getAccountBalance()))
                .collect(Collectors.toSet());

//        Set<Map<String, BigDecimal>>  maps = new HashSet<>();
//
//        employee.get().getClients().forEach(client -> {
//            Map<String, BigDecimal> clientMap = new HashMap<>();
//            clientMap.put(client.getName(), client.getAccountBalance());
//
//            maps.add(clientMap);
//        });
       // map.entrySet().forEach(bigDecimalClientEntry ->
          //      map.entrySet());
//        clientMap.entrySet().stream()
//                .collect(Collectors.toList());
//      return maps;
    }

}
