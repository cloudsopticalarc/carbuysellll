package com.spring.jwt.service;

import com.spring.jwt.Interfaces.BeadingCarService;
import com.spring.jwt.dto.BeadingCAR.BeadingCARDto;
import com.spring.jwt.entity.BeadingCAR;
import com.spring.jwt.repository.BeadingCarRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class BeadingCarServiceImpl implements BeadingCarService {

    private final BeadingCarRepo beadingCarRepo;

    @Override
    public String AddBCar(BeadingCARDto beadingCARDto) {
        BeadingCAR beadingCAR = new  BeadingCAR(beadingCARDto);
        beadingCarRepo.save(beadingCAR);
        return "car added";
    }

    @Override
    public String editCarDetails(BeadingCARDto beadingCARDto, Integer beadingCarId) {
        return null;
    }

    @Override
    public List<BeadingCARDto> getAllBeadingCars() {
        return null;
    }

    @Override
    public String deleteBCar(Integer beadingCarId) {
        return null;
    }

    @Override
    public BeadingCARDto getBCarById(Integer beadingCarId) {
        return null;
    }
}
