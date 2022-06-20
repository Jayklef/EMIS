package com.jerrycodes.emis.service.impl;

import com.jerrycodes.emis.entity.Department;
import com.jerrycodes.emis.entity.Employee;
import com.jerrycodes.emis.exception.DepartmentNotFoundException;
import com.jerrycodes.emis.model.DepartmentDto;
import com.jerrycodes.emis.repository.DepartmentRepository;
import com.jerrycodes.emis.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> findAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Department::getName))
                .collect(Collectors.toList());
    }

    @Override
    public Department addDepartment(DepartmentDto departmentDto) {

                Department department = Department
                         .builder()
                         .name(departmentDto.getName())
                         .location(departmentDto.getLocation())
                         .build();

              return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(Department department, Long id) {

        Department departmentInDb = departmentRepository.findById(id).get();

        if (Objects.nonNull(department.getName())&&
        !"".equalsIgnoreCase(department.getName())){
            departmentInDb.setName(department.getName());
        }

        if (Objects.nonNull(department.getLocation()) &&
        !"".equalsIgnoreCase(department.getLocation())){
            departmentInDb.setLocation(department.getLocation());
        }

        return departmentRepository.save(department);
    }

    @Override
    public Department findDepartmentById(Long id) {

        Optional<Department> department = departmentRepository.findById(id);

        if (!department.isPresent() || department == null){
            throw new RuntimeException("Department not found");
        }
        return departmentRepository.findById(id).get();
    }

    @Override
    public List<Employee> findAllEmployeesByDepartment(Long id, Employee employee) {

        Optional<Department> department = departmentRepository.findById(id);

        if (department.isEmpty() || department == null){
            throw new DepartmentNotFoundException("department with id not found" + id);
        }
        return departmentRepository.findById(id)
                .get()
                .getEmployees()
                .stream()
                .sorted(Comparator.comparing(Employee::getFirstname))
                .collect(Collectors.toList());
    }

    @Override
    public Long findNumberOfDepartment() {
       return departmentRepository.findAll()
                .stream()
               .count();
    }

    @Override
    public boolean deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id).get();
        departmentRepository.delete(department);
        return true;
    }
}
