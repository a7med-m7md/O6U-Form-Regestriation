package com.o6u.o6uform.services;

import com.o6u.o6uform.entities.Student;
import com.o6u.o6uform.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    public static final Integer limitG1 = 10, limitG2 = 10, limitG3 = 10;
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<String> getAvailableGroupsByBundle(String bundle){
        List<String> avialableGroups = new ArrayList<>();

        Integer groupACount = studentRepository.countByBundleAndGroupp(bundle, "Group 1"); // 0
        System.out.println(groupACount);
        if(groupACount < limitG1) avialableGroups.add("Group 1");
        Integer groupBCount = studentRepository.countByBundleAndGroupp(bundle, "Group 2");
        System.out.println(groupBCount);
        if(groupBCount < limitG2) avialableGroups.add("Group 2");
        Integer groupCCount = studentRepository.countByBundleAndGroupp(bundle, "Group 3");
        System.out.println(groupCCount);
        if(groupCCount < limitG3) avialableGroups.add("Group 3");

        return avialableGroups;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_COMMITTED)
    public ResponseEntity<String> submitStudentInfo(Student student){
        Integer studentCount = studentRepository.countByBundleAndGroupp(student.getBundle(), student.getGroupp());
        System.out.println(studentCount);
        if(studentCount >= getLimitedNumberForGroup(student.getGroupp())) throw new IllegalArgumentException("This Group no longer available");
        studentRepository.save(student);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private Integer getLimitedNumberForGroup(String groupName){
        switch (groupName){
            case "Group 1": return limitG1;
            case "Group 2": return limitG2;
            case "Group 3": return limitG3;
            default: return 0;
        }
    }
}
