package com.spring.jwt.controller;

import com.spring.jwt.Interfaces.PlacedBidService;
import com.spring.jwt.dto.BeedingDtos.PlacedBidDTO;
import com.spring.jwt.dto.ResponseAllPlacedBidDTO;
import com.spring.jwt.dto.ResponseDto;
import com.spring.jwt.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/Bid")
@RequiredArgsConstructor
public class PlaceBidController {

    private final PlacedBidService placedBidService;

        @PostMapping("/placeBid")
    private ResponseEntity<?> placeBid(@RequestBody PlacedBidDTO placedBidDTO, @RequestParam Integer bidCarId) {
            try {
                String result = placedBidService.placeBid(placedBidDTO, bidCarId);
                return (ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("success", result)));
            } catch (BidAmountLessException | BidForSelfAuctionException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bid amount smaller than highest bid");
            }
        }


}
