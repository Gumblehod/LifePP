package com.anq.LifePP.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.anq.LifePP.Entity.ShopEntity;

@Repository
public interface ShopRepository extends JpaRepository<ShopEntity, Integer>{
	
}