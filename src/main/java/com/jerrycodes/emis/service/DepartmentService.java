package com.jerrycodes.emis.service;

import com.jerrycodes.emis.entity.Department;
import com.jerrycodes.emis.entity.Employee;
import com.jerrycodes.emis.model.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    List<Department> findAllDepartments();

    Department addDepartment(DepartmentDto departmentDto);

    Department updateDepartment(Department department, Long id);

    Department findDepartmentById(Long id);

    List<Employee> findAllEmployeesByDepartment(Long id, Employee employee);

    Long findNumberOfDepartment();

    boolean deleteDepartment(Long id);
}
