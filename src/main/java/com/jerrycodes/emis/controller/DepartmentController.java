package com.jerrycodes.emis.controller;

import com.jerrycodes.emis.entity.Department;
import com.jerrycodes.emis.model.DepartmentDto;
import com.jerrycodes.emis.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("departments")
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

    @PostMapping("/new")
    public ResponseEntity<Department> saveDepartment(@RequestBody DepartmentDto departmentDto){
        Department newDepartment = departmentService.addDepartment(departmentDto);
        return new ResponseEntity<>(newDepartment , HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable("id") Long id){
        Department department = departmentService.findDepartmentById(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department,
                                                       @PathVariable Long id){
        Department updatedDepartment = departmentService.updateDepartment(department, id);
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }
}
