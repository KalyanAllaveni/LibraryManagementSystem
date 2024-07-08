package com.example.Library.Controllers;

import com.example.Library.Entities.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.example.Library.DTO.StudentDTO;
import com.example.Library.Entities.Student;
import com.example.Library.Services.StudentService;

import org.springframework.beans.factory.annotation.Autowired;


@RestController
// @RequestBody
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping
    public Student saveStudentDetails(@RequestBody StudentDTO studentDTO) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user);
        return studentService.addStudent(studentDTO, user);
    }

    @GetMapping("/{id}")
    public Student  getStudentByID(@PathVariable long id) {
        return studentService.getStudentDetailsById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deletedStudent(@PathVariable long id){
        return studentService.deleteStudentDetailsById(id);
    }

    @PatchMapping("/update")
    public Student putStudent(@RequestBody StudentDTO studentDTO) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return studentService.updateStudent(studentDTO,user.getStudent().getStudent_id());

    }
}
