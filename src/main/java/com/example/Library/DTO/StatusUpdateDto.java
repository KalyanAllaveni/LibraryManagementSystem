package com.example.Library.DTO;

import com.example.Library.Enum.StausUpdateEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StatusUpdateDto {
    private long request_Id;
    private StausUpdateEnum status;
}

