package com.spring.jwt.service;

import com.spring.jwt.Interfaces.PlacedBidService;
import com.spring.jwt.dto.BeedingDtos.PlacedBidDTO;
import com.spring.jwt.entity.BidCars;
import com.spring.jwt.entity.PlacedBid;
import com.spring.jwt.exception.BidAmountLessException;
import com.spring.jwt.exception.UserNotFoundExceptions;
import com.spring.jwt.repository.BidCarsRepo;
import com.spring.jwt.repository.PlacedBidRepo;
import com.spring.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PlacedBidServiceImpl implements PlacedBidService {
      private final PlacedBidRepo placedBidRepo;

      private final  BidCarsRepo bidCarsRepo;

      private final ModelMapper modelMapper;

      private final UserRepository userRepository;

    @Override
    public String placeBid(PlacedBidDTO placedBidDTO, Integer bidCarId) throws BidAmountLessException {
        Optional<BidCars> byId = bidCarsRepo.findById(bidCarId);
        if (byId.isEmpty()){
            throw new UserNotFoundExceptions("Bid Cannot Be Placed as Car is Not Found in Our Database");
        }
        PlacedBid placedBid = convertToEntity(placedBidDTO);
        if(placedBid.getAmount()< byId.get().getBasePrice()){

            throw new BidAmountLessException();
        }

          placedBid.setBidCarId(bidCarId);
          placedBidRepo.save(placedBid);

        return "Bid Placed Successfully";
    }

}
