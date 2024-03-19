package com.spring.jwt.dto;

import com.spring.jwt.dto.BeedingDtos.PlacedBidDTO;
import lombok.Data;

import java.util.List;
@Data
public class ResponseAllPlacedBidDTO {

    private String message;
    private List<PlacedBidDTO> list;
    private String exception;

    public ResponseAllPlacedBidDTO(String message){
        this.message=message;
    }

}


