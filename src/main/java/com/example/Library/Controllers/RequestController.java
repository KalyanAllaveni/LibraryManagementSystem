package com.example.Library.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.example.Library.DTO.RequestDTO;
import com.example.Library.Entities.Request;
import com.example.Library.Entities.User;
import com.example.Library.Services.RequestService;

@RestController
@RequestMapping("/request")
public class RequestController {
    @Autowired
    RequestService requestService;

    @PostMapping()
    public Request postMethodName(@RequestBody RequestDTO requestDTO) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return requestService.createRequest(requestDTO, user);
    }

    @DeleteMapping("/{id}")
    public String deleteRequest(@PathVariable long id) {
        requestService.deleteRequest(id);
        return "Request Deleted Successfully";
    }

    @GetMapping("/{id}")
    public Request getRequestById(@PathVariable long id) {
        return requestService.getRequestById(id);
    }
}
