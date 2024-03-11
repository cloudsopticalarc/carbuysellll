package com.spring.jwt.repository;

import com.spring.jwt.entity.BeadingCAR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BeadingCarRepo extends JpaRepository<BeadingCAR, Integer>, JpaSpecificationExecutor<BeadingCAR> {
}
