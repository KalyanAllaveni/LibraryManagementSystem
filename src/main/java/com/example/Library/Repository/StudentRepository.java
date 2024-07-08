package com.example.Library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Library.Entities.Student;

public interface StudentRepository extends JpaRepository<Student,Long>{
    
}
