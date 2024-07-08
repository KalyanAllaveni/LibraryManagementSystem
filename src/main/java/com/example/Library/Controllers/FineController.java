package com.example.Library.Controllers;

import com.example.Library.Services.FineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class FineController {
    FineService fineService;
    @GetMapping("fine/{id}")
    public double getFineAmountById(@PathVariable long id){
        return fineService.getFineById(id);
    }
}
