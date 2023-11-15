package com.anq.LifePP.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.anq.LifePP.Entity.AchievementEntity;

@Repository
public interface AchievementRepository extends JpaRepository<AchievementEntity, Integer>{
	
}