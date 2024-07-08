package com.example.Library.DTO;

import java.util.List;

import com.example.Library.Enum.RequestType;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {
    private Long book_id;
    private RequestType requestType;
}
