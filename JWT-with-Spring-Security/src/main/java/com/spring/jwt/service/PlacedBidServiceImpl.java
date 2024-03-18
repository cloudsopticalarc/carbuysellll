package com.spring.jwt.service;

import com.spring.jwt.Interfaces.PlacedBidService;
import com.spring.jwt.dto.BeedingDtos.PlacedBidDTO;
import com.spring.jwt.entity.BidCars;
import com.spring.jwt.entity.PlacedBid;
import com.spring.jwt.exception.*;
import com.spring.jwt.repository.BidCarsRepo;
import com.spring.jwt.repository.PlacedBidRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlacedBidServiceImpl implements PlacedBidService {
      private final PlacedBidRepo placedBidRepo;

      private final BidCarsRepo bidCarsRepo;

      private final ModelMapper modelMapper;

    @Override
    public String placeBid(PlacedBidDTO placedBidDTO, Integer bidCarId) throws BidAmountLessException, BidForSelfAuctionException {
        Optional<BidCars> byId = bidCarsRepo.findById(bidCarId);
        if (byId.isEmpty()){
            throw new CarNotFoundException("Bid Cannot Be Placed as Car is Not Found in Our Database");
        }
        PlacedBid placedBid = convertToEntity(placedBidDTO);

        if(placedBid.getAmount()< byId.get().getBasePrice()){
            throw new BidAmountLessException();
        }if(placedBid.getUserId()==byId.get().getUserId()){
            throw new BidForSelfAuctionException();
        }

          placedBid.setBidCarId(bidCarId);
          placedBidRepo.save(placedBid);

        return "Bid Placed Successfully";
    }

    public PlacedBid convertToEntity(PlacedBidDTO placedBidDTO){
        PlacedBid toEntity = modelMapper.map(placedBidDTO, PlacedBid.class);
        return toEntity;
    }

    public PlacedBidDTO convertToDto(PlacedBid placedBid){
        PlacedBidDTO toDto = modelMapper.map(placedBid, PlacedBidDTO.class);
        return toDto;
    }

}
