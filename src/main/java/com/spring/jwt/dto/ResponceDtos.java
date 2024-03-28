package com.spring.jwt.dto;

import lombok.Data;

@Data
public class ResponceDtos {
    public String message;
    public Integer totalPages;
    public Object object;


    public ResponceDtos(String message, Object object) {
        this.message = message;
        this.object = object;

    }
}
