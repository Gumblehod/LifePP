package com.anq.LifePP.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anq.LifePP.Entity.AchievementEntity;
import com.anq.LifePP.Repository.AchievementRepository;

@Service
public class AchievementService {
	
	@Autowired
	AchievementRepository arepo;
	
	public AchievementEntity insertAchievement(AchievementEntity e) {
		return arepo.save(e);
	}
	
	public List<AchievementEntity> getallAchievement(){
		return arepo.findAll();
	}
	
	public AchievementEntity updateAchievement(int id, AchievementEntity a) {
		AchievementEntity ac = new AchievementEntity();
			try {
				ac = arepo.findById(id).get();
				ac.setName(a.getName());
				ac.setDescription(a.getDescription());
			}
			catch(NoSuchElementException ex) {
				throw new NoSuchElementException("Achievement "+id+"doesn't exist.");
			}finally {
				return arepo.save(ac);
			}
	}
	
	public String deleteAchievement(int id) {
		String msg = "";
		
			if(arepo.findById(id).get()!=null) {
				arepo.deleteById(id);
				msg = "Achievement " + id + "has been deleted";
			}
			else {msg = "Achievement " + id + "doesnt't exist";}
			
			return msg;
	}
}
