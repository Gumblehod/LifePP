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

	public List<AchievementEntity> getallAchievement() {
		return arepo.findAll();
	}

	public AchievementEntity updateAchievement(int id, AchievementEntity a) {
		AchievementEntity ac = arepo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Achievement " + id + "does not exist"));

		ac.setName(a.getName());
		ac.setDescription(a.getDescription());

		return arepo.save(ac);
	}

	public String deleteAchievement(int id) {
		AchievementEntity ac = arepo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Achievement " + id + "does not exist"));

		if (ac.isDeleted()) {
			return "Achievement #" + id + " is already deleted!";
		} else {
			ac.setDeleted(true);
			arepo.save(ac);
			return "Achievement #" + id + "has been deleted";
		}
	}
}
