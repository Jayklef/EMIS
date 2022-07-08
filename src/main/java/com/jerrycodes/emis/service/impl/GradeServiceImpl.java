package com.jerrycodes.emis.service.impl;

import com.jerrycodes.emis.entity.Grade;
import com.jerrycodes.emis.model.GradeDto;
import com.jerrycodes.emis.repository.GradeRepository;
import com.jerrycodes.emis.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    private GradeRepository gradeRepository;
    @Autowired
    public GradeServiceImpl(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Grade> findAllGrades() {
        return gradeRepository.findAll();
    }

    @Override
    public Grade saveGrade(GradeDto gradeDto) {

        Grade grade = Grade.builder()
                .name(gradeDto.getName())
                .minSalary(gradeDto.getMinSalary())
                .maxSalary(gradeDto.getMaxSalary())
                .build();
        return gradeRepository.save(grade);
    }
}
