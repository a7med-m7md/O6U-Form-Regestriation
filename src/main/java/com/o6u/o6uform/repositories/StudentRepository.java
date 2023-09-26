package com.o6u.o6uform.repositories;


import com.o6u.o6uform.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Integer countByBundleAndGroupp(String bundle, String Groupp);

}
