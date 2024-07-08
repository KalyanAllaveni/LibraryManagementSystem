package com.example.Library.Controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.example.Library.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.example.Library.DTO.AdminDTO;
import com.example.Library.DTO.StatusUpdateDto;
import com.example.Library.Entities.Admin;
import com.example.Library.Entities.Request;
import com.example.Library.Services.AdminService;

@RestController()
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping
    public Admin addAdmin(@RequestBody AdminDTO asAdminDTO) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return adminService.addAdmin(asAdminDTO, user);
    }

    @PutMapping("/update/{id}")
    public Admin upAdmin(@PathVariable long id, @RequestBody AdminDTO adminDTO) {
        return adminService.update(id, adminDTO);
    }

    @GetMapping("/{id}")
    public Optional<Admin> getMethodName(@RequestParam long id) {
        return adminService.getAdminDetailsById(id);
    }

    @DeleteMapping("/{id}")
    public String admin(@PathVariable long id) {
        return adminService.deleteAdminDetailsById(id);
    }

    @GetMapping("s")
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @PostMapping("/request-update")
    public Request updateRequest(@RequestBody StatusUpdateDto statusUpdateDto) throws ParseException {

        return this.adminService.requestResponseFromAdmin(statusUpdateDto, null);
    }

    @GetMapping("/requests/{id}")
    public List<Request> getRequests(@PathVariable long id) {
        return adminService.getRequests(id);
    }

}
