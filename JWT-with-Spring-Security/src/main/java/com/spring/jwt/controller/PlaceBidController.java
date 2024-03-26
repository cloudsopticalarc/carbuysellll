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

    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseAllPlacedBidDTO> getPlacedBidsByUserId(@PathVariable Integer userId) {
        List<PlacedBidDTO> placedBids = null;
        try {
            placedBids = placedBidService.getByUserId(userId);
            return ResponseEntity.ok(new ResponseAllPlacedBidDTO("Placed bids retrieved successfully"));
        } catch (UserNotFoundExceptions e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseAllPlacedBidDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseAllPlacedBidDTO("An error occurred: " + e.getMessage()));
        }
    }
    @GetMapping("/car/{bidCarId}")
    public ResponseEntity<ResponseAllPlacedBidDTO> getPlacedBidsByCarId(@PathVariable Integer bidCarId) {
        try {
            List<PlacedBidDTO> placedBids = placedBidService.getByCarID(bidCarId);
            return ResponseEntity.ok(new ResponseAllPlacedBidDTO("Placed bids for car ID " + bidCarId + " retrieved successfully"));
        } catch (BidNotFoundExceptions e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseAllPlacedBidDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseAllPlacedBidDTO("An error occurred: " + e.getMessage()));
        }
    }

    @GetMapping("/getPlacedBid/{placedBidId}")
    public ResponseEntity<ResponseAllPlacedBidDTO> getPlacedBidById(@PathVariable Integer placedBidId) {
        try {
            PlacedBidDTO placedBid = placedBidService.getById(placedBidId);
            return ResponseEntity.ok(new ResponseAllPlacedBidDTO("Placed bid with ID " + placedBidId + " retrieved successfully"));
        } catch (PlacedBidNotFoundExceptions e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseAllPlacedBidDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseAllPlacedBidDTO("An error occurred: " + e.getMessage()));
        }
    }

    @GetMapping("/topThree/{bidCarId}")
    public ResponseEntity<ResponseAllPlacedBidDTO> getTopThreeBids(@PathVariable Integer bidCarId) {
        try {
            List<PlacedBidDTO> topThreeBids = placedBidService.getTopThree(bidCarId);
            return ResponseEntity.ok(new ResponseAllPlacedBidDTO("Top three bids for car ID " + bidCarId + " retrieved successfully"));
        } catch (BidNotFoundExceptions e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseAllPlacedBidDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseAllPlacedBidDTO("An error occurred: " + e.getMessage()));
        }
    }

}
