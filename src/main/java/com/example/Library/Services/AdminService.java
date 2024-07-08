package com.example.Library.Services;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.Library.Entities.*;
import com.example.Library.Repository.FineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.Library.DTO.AdminDTO;
import com.example.Library.DTO.StatusUpdateDto;
import com.example.Library.Enum.BookStatus;
import com.example.Library.Enum.RequestType;
import com.example.Library.Enum.Request_status;
import com.example.Library.Enum.StausUpdateEnum;
import com.example.Library.Enum.Transaction_status;
import com.example.Library.Repository.AdminRepository;
import com.example.Library.Repository.RequestRepository;
import com.example.Library.Repository.TransactionsRepository;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    TransactionsRepository transactionsRepository;
    @Autowired
    StudentService studentService;
    @Autowired
    BookService bookService;
    @Autowired
    FineRepository fineRepository;

    public Admin addAdmin(AdminDTO adminDTO, User user) {
        Admin admin = adminDTO.convertToAdmin(user);
        return adminRepository.save(admin);
    }

    public Optional<Admin> getAdminDetailsById(long id) {
        return adminRepository.findById(id);
    }


    public String deleteAdminDetailsById(long id) {
        adminRepository.deleteById(id);
        return "deleted successfully";
    }

    public Admin update(long id, AdminDTO adminDTO) {
        Optional<Admin> admin = adminRepository.findById(id);
        if (admin.isPresent()) {
            Admin admin2 = admin.get();
            if (adminDTO.getName() != null) {
                admin2.setName(adminDTO.getName());
            }
            this.adminRepository.save(admin2);
            return admin2;
        }
        throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Admin not found");
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public List<Request> getRequests(long id) {
        Admin admin = this.adminRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Admin not found"));
        return admin.getRequest();
    }

    public Request requestResponseFromAdmin(StatusUpdateDto statusUpdateDto, User user) {
        Request request = requestRepository.findById(statusUpdateDto.getRequest_Id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Request not Found"));
        Student student = request.getStudent();
        Book book = request.getBook();

        if (!(request.getAdmin().getAdmin_id() == user.getAdmin().getAdmin_id())) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),
                    "This request doesn't belong to current admin");
        }

        if (request.getReq_status().equals(Request_status.Pending)) {

            if (request.getReq_type().equals(RequestType.RETURN)) {

                List<Request> requests = this.requestRepository
                        .findAllByStudentOrderByDateDesc(student)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Invlaid Request"));

                for (Request request1 : requests) {
                    if (request1.getReq_status().equals(Request_status.Accepted)
                            && request1.getReq_type().equals(RequestType.ISSUE)
                            && request1.getBook().getBook_id() == request.getBook().getBook_id()) {
                        Date issueDate = request1.getDate();
                        Date returnDate = request.getDate();
                        long time_difference = returnDate.getTime() - issueDate.getTime();
                        long days_difference = (time_difference / (1000 * 60 * 60 * 24)) % 365;
                        if (days_difference > 7) {
                            double amount = days_difference * 5;
                            Fine fine = new Fine();
                            fine.setAmount(amount);
                            fine.setStudent(request.getStudent());
                            fine.setDescription("you didn't return book within time");
                            fine.setImposedDate(issueDate);
                            fineRepository.save(fine);
                        }
                        book.setStatus(BookStatus.AVAILABLE);
                        break;
                    }
                    throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Invalid request");
                }
                ;
            }

            if (statusUpdateDto.getStatus().equals(StausUpdateEnum.Approved)) {
                request.setReq_status(Request_status.Accepted);
            } else {
                request.setReq_status(Request_status.NotAccepted);
            }

            Transactions transaction = Transactions.builder()
                    .request(request)
                    .status(request.getReq_status().equals(Request_status.Accepted) ? Transaction_status.Completed
                            : Transaction_status.Rejected)
                    .build();
            transactionsRepository.save(transaction);
            bookService.savebook(book);
            return requestRepository.save(request);
        }
        throw new ResponseStatusException(HttpStatusCode.valueOf(404),
                "Request is already Completed" + request.getReq_status().name());
    }

}
