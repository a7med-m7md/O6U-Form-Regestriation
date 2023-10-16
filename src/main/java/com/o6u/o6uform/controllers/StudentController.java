package com.o6u.o6uform.controllers;

import com.o6u.o6uform.entities.Student;
import com.o6u.o6uform.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class StudentController {

    private StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("avialableGroups")
    public List<String> getAvialableGroups(@RequestParam String bundle){
        System.out.println(bundle);
        return service.getAvailableGroupsByBundle(bundle);
    }

    
    @PostMapping("student")
    public ResponseEntity<String> saveStudent(@RequestBody Student student){
        System.out.println(student);
        service.submitStudentInfo(student);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
