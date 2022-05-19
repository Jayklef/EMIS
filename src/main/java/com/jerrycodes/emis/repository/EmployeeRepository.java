package com.jerrycodes.emis.repository;

import com.jerrycodes.emis.entity.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByGender(String gender, Pageable pageable);

    ArrayList<Employee> findAllByBirthdate(Date birthdate);
    /*Pageable findByGender(String gender, Pageable pageable);*/
}
