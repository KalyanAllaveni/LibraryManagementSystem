package com.example.Library.Repository;

import com.example.Library.Entities.Book;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Library.Entities.Request;
import com.example.Library.Entities.Student;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {

    Optional<List<Request>> findAllByStudentOrderByDateDesc(Student student);

}
