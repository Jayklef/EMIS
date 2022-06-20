package com.jerrycodes.emis.controller;

import com.jerrycodes.emis.entity.Department;
import com.jerrycodes.emis.entity.Employee;
import com.jerrycodes.emis.model.DepartmentDto;
import com.jerrycodes.emis.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/departments")
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/dept_list")
    public ResponseEntity<List<Department>> getAllDepartments(){
        List<Department> departments = departmentService.findAllDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Department> saveDepartment(@Valid @RequestBody DepartmentDto departmentDto){
        Department newDepartment = departmentService.addDepartment(departmentDto);
        return new ResponseEntity<>(newDepartment , HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable("id") Long id){
        Department department = departmentService.findDepartmentById(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Department> updateDepartment(@PathVariable("id") Long id,
                                                       @RequestBody Department department){
        Department updatedDepartment = departmentService.updateDepartment(department, id);
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }
    @GetMapping("/{id}/employees")
    public ResponseEntity<List<Employee>> getAllEmployeesByDepartment(@PathVariable("id") Long id, Employee employee){
        List<Employee> employees = departmentService.findAllEmployeesByDepartment(id, employee);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    @GetMapping("/{id}/departmentscount")
    public Long getDepartmentNumber(){
        return departmentService.findNumberOfDepartment();
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteDepartment(@PathVariable("id") Long id){
        boolean deleted = false;
        deleted = departmentService.deleteDepartment(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }
}
