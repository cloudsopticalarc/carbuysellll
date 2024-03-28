package com.spring.jwt.dto;

import com.spring.jwt.dto.BookingDtos.PendingBookingDto;
import lombok.Data;

import java.util.List;

@Data

public class ResponseAllPendingBookingDto {
    private String message;
    private List<PendingBookingDto> list;
    private String exception;

    public ResponseAllPendingBookingDto(String message){
        this.message=message;
    }

}
