package com.anq.LifePP.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.anq.LifePP.Entity.CoachEntity;

@Repository
public interface CoachRepository extends JpaRepository<CoachEntity, Integer>{
	 CoachEntity findByUsername(String username);
}