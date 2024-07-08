package com.example.Library.Services;

import com.example.Library.DTO.BookResponseDTO;
import com.example.Library.Entities.Fine;
import com.example.Library.Repository.FineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class FineService {
    @Autowired
    FineRepository fineRepository;

    public double getFineById(long id){
       Fine fine=fineRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Fine not present"));
       return fine.getAmount();
    }

}
