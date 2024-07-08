

package com.example.Library.Services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.Library.DTO.RequestDTO;
import com.example.Library.Entities.Admin;
import com.example.Library.Entities.Book;
import com.example.Library.Entities.Request;
import com.example.Library.Entities.Student;
import com.example.Library.Entities.User;
import com.example.Library.Enum.BookStatus;
import com.example.Library.Enum.RequestType;
import com.example.Library.Enum.Request_status;
import com.example.Library.Repository.RequestRepository;

@Service
public class RequestService {
    @Autowired
    private StudentService studentService;
    @Autowired
    private BookService bookService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private RequestRepository requestRepository;

    public Request createRequest(RequestDTO requestDTO, User user) {
        Student student = studentService.getStudentDetailsById(user.getStudent().getStudent_id());

        Book book = bookService.getBook(requestDTO.getBook_id());

        if (requestDTO.getRequestType() == RequestType.ISSUE) {
            if (book.getStatus() != BookStatus.AVAILABLE) {
                throw new ResponseStatusException(
                        HttpStatusCode.valueOf(404),
                        "book is Unavailable");
            }
        } else {
            if (book.getStatus() != BookStatus.NOT_AVAILABLE) {
                throw new ResponseStatusException(
                        HttpStatusCode.valueOf(404),
                        "book is Unavailable");
            }
        }

        List<Admin> admins = adminService.getAllAdmins();
        int smallest = admins.get(0).getRequest().size();
        Admin adminAlloted = admins.get(0);

        for (Admin admin : admins) {
            if (admin.getRequest().size() < smallest) {
                smallest = admin.getRequest().size();
                adminAlloted = admin;
            }
        }

        Request request = Request.builder()
                .admin(adminAlloted)
                .book(book)
                .req_status(Request_status.Pending)
                .student(student)
                .req_type(requestDTO.getRequestType())
                .date(new Date())
                .build();

        if (requestDTO.getRequestType().equals(RequestType.ISSUE)) {
            book.setStatus(BookStatus.NOT_AVAILABLE);
        }
        bookService.savebook(book);

        return requestRepository.save(request);
    }

    public void deleteRequest(long id) {
        requestRepository.deleteById(id);
    }

    public Request getRequestById(long id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Request not present"));
    }

    public Request saveRequest(Request request) {
        return this.requestRepository.save(request);
    }
}
