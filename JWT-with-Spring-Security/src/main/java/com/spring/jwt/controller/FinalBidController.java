package com.spring.jwt.controller;


import com.spring.jwt.dto.FinalBidDto;
import com.spring.jwt.entity.Final1stBid;
import com.spring.jwt.exception.*;
import com.spring.jwt.service.Final1stBidServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/finalBid")
@RequiredArgsConstructor
public class FinalBidController {
    private final Final1stBidServiceImpl final1stBidService;

    @PostMapping("/place")
    public ResponseEntity<String> finalPlaceBid(@RequestBody FinalBidDto finalBidDto, @RequestParam Integer bidCarId) {
        try {
            String result = final1stBidService.FinalPlaceBid(finalBidDto, bidCarId);
            return ResponseEntity.ok(result);
        } catch (BidAmountLessException | BidForSelfAuctionException | BeadingCarNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FinalBidDto>> getByUserId(@PathVariable Integer userId) {
        try {
            List<FinalBidDto> bids = final1stBidService.getByUserId(userId);
            return ResponseEntity.ok(bids);
        } catch (UserNotFoundExceptions e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/car/{bidCarId}")
    public ResponseEntity<FinalBidDto> getByCarID(@PathVariable Integer bidCarId) {
        try {
            FinalBidDto bid = final1stBidService.getByCarID(bidCarId);
            return ResponseEntity.ok(bid);
        } catch (BidNotFoundExceptions e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/placed/{placedBidId}")
    public ResponseEntity<Final1stBid> getByPlacedBidId(@PathVariable Integer placedBidId) {
        try {
            Final1stBid bid = final1stBidService.getByPlacedBidId(placedBidId);
            return ResponseEntity.ok(bid);
        } catch (PlacedBidNotFoundExceptions e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{placedBidId}")
    public ResponseEntity<FinalBidDto> getById(@PathVariable Integer placedBidId) {
        try {
            FinalBidDto bid = final1stBidService.getById(placedBidId);
            return ResponseEntity.ok(bid);
        } catch (PlacedBidNotFoundExceptions e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/top/{bidCarId}")
    public ResponseEntity<FinalBidDto> getTopOne(@PathVariable Integer bidCarId) {
        try {
            FinalBidDto bid = final1stBidService.getTopOne(bidCarId);
            return ResponseEntity.ok(bid);
        } catch (BidNotFoundExceptions e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}

