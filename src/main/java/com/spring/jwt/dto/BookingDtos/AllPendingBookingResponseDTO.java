package com.spring.jwt.dto.BookingDtos;

import lombok.Data;

import java.util.List;

@Data

public class AllPendingBookingResponseDTO {
    private String message;
    private List<PendingBookingDto> list;
    private String exception;

    public AllPendingBookingResponseDTO(String message){
        this.message=message;
    }

}
