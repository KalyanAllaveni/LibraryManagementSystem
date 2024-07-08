package com.example.Library.Services;

import com.example.Library.Entities.Admin;
import com.example.Library.Entities.Request;
import com.example.Library.Enum.Request_status;
import com.example.Library.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TransactionService {
    @Autowired
    AdminRepository adminRepository;

    public List<Request> getPendingRequestByAdminId(long id){
        Admin admin = adminRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatusCode.valueOf(404),"Admin with Id"+id+"is not found"));
        List<Request> requestList = admin.getRequest();

        ArrayList<Request> pendingRequestList = new ArrayList<>();

        for(Request request: requestList){
            if(request.getReq_status().equals(Request_status.Pending)){
                pendingRequestList.add(request);
            }
        }
        return pendingRequestList;




//        return  requestList;

    }
}
