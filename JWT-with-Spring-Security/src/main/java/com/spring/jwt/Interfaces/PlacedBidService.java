package com.spring.jwt.Interfaces;

import com.spring.jwt.dto.BeedingDtos.PlacedBidDTO;
import com.spring.jwt.exception.BidAmountLessException;
import com.spring.jwt.exception.BidForSelfAuctionException;

public interface PlacedBidService {
    public String placeBid(PlacedBidDTO placedBidDTO, Integer bidCarId) throws BidAmountLessException, BidForSelfAuctionException;


}
