package com.jerrycodes.emis.controller;

import com.jerrycodes.emis.entity.Grade;
import com.jerrycodes.emis.model.GradeDto;
import com.jerrycodes.emis.service.GradeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/grades")
public class GradeController {

    private GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("/all-grades")
    public ResponseEntity<List<Grade>> findAllGrades(){
        List<Grade> grades = gradeService.findAllGrades();
        return new ResponseEntity<>(grades, HttpStatus.OK);
    }

    @PostMapping("/new-grade")
    public ResponseEntity<Grade> createGrade(@Valid @RequestBody GradeDto gradeDto){
        Grade newGrade = gradeService.saveGrade(gradeDto);
        return new ResponseEntity<>(newGrade, HttpStatus.CREATED);
    }
}
