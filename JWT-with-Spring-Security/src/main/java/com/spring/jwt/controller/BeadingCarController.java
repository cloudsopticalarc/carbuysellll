package com.spring.jwt.controller;

import com.spring.jwt.Interfaces.BeadingCarService;
import com.spring.jwt.dto.BeadingCAR.BeadingCARDto;
import com.spring.jwt.dto.ResponseDto;
import com.spring.jwt.exception.BeadingCarNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("BeadingCarController")
public class BeadingCarController {

    @Autowired
    private BeadingCarService beadingCarService;

    @PostMapping(value = "/carregister")
    public ResponseEntity<ResponseDto> carRegistration(@RequestBody BeadingCARDto beadingCARDto) {
        try{
            String result = beadingCarService.AddBCar(beadingCARDto);
            return (ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("success",result)));
        }catch (BeadingCarNotFoundException beadingCarNotFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess","Dealer not found"));
        }
    }

}
