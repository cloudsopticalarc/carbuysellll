package com.spring.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.jwt.entity.PlacedBid;

public interface PlacedBidRepo extends JpaRepository<PlacedBid, Integer> {

    List<PlacedBid> findByUserId(Integer userId);

    List<PlacedBid> findByBidCarId(Integer bidCarId);

    @Query("SELECT pb FROM PlacedBid pb WHERE pb.bidCarId = :bidCarId ORDER BY pb.amount DESC LIMIT 3")
    List<PlacedBid> findTop3ByBidCarIdOrderByAmountDesc(Integer bidCarId);
}
