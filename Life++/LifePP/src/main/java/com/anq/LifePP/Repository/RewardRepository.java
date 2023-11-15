package com.anq.LifePP.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.anq.LifePP.Entity.RewardEntity;

@Repository
public interface RewardRepository extends JpaRepository<RewardEntity, Integer>{
	
}