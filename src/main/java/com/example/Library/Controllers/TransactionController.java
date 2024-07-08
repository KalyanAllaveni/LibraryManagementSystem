package com.example.Library.Controllers;

import com.example.Library.Entities.Request;
import com.example.Library.Enum.Request_status;
import com.example.Library.Services.StudentService;
import com.example.Library.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

  @Autowired
  StudentService studentService;

  @Autowired
  TransactionService transactionService;

  // student id transactions
  // pending requests of Admin by adminId
  // pending requests of student by studentId
  @GetMapping("/student/pending/{id}")
  public List<Request> getPendingRequests(@PathVariable long id) {
    return studentService.getPendingRequestByStudentId(id);
  }

  @GetMapping("/admin/pending/{id}")
  public List<Request> getPendingRequestsByAdminId(@PathVariable long id) {
    return transactionService.getPendingRequestByAdminId(id);
  }
}
