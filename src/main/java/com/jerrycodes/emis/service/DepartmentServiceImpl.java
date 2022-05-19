package com.jerrycodes.emis.service;

import com.jerrycodes.emis.entity.Department;
import com.jerrycodes.emis.model.DepartmentDto;
import com.jerrycodes.emis.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> findAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department addDepartment(DepartmentDto departmentDto) {

                Department department = Department
                         .builder()
                         .name(departmentDto.getName())
                         .location(departmentDto.getLocation())
                         .build();
              return department;
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
}
