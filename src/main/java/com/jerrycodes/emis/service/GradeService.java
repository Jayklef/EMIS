package com.jerrycodes.emis.service;

import com.jerrycodes.emis.entity.Grade;
import com.jerrycodes.emis.model.GradeDto;

import java.util.List;

public interface GradeService {
    List<Grade> findAllGrades();

    Grade saveGrade(GradeDto gradeDto);
}
