package com.spring.jwt.repository;

import com.spring.jwt.entity.PlacedBid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlacedBidRepo extends JpaRepository<PlacedBid, Integer> {
}
