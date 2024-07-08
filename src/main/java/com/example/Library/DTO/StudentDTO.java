package com.example.Library.DTO;

import com.example.Library.Entities.Student;
import com.example.Library.Entities.User;
import com.example.Library.Enum.StudentStatus;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class StudentDTO {
    String name;
    String branch;
    String contact;
    StudentStatus status;

    public Student convertToStudent(User user) {
        Student student = new Student();
        student.setName(this.getName());
        student.setBranch(this.getBranch());
        student.setContact(this.getContact());
        student.setStatus(this.getStatus());
        student.setEmail(user.getUsername());
        student.setUser(user);
        return student;
    }

}
