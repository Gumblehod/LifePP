package com.anq.LifePP.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anq.LifePP.Entity.ChallengeEntity;
import com.anq.LifePP.Repository.ChallengeRepository;

@Service
public class ChallengeService {

	@Autowired
	ChallengeRepository repo;

	public ChallengeEntity insertChallenge(ChallengeEntity e) {
		return repo.save(e);
	}

	public List<ChallengeEntity> getallChallenge() {
		return repo.findAll();
	}

	public ChallengeEntity updateChallenge(int id, ChallengeEntity c) {
		ChallengeEntity e = repo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Challenge " + id + "doesn't exist."));

		e.setTitle(c.getTitle());
		e.setAchievement(c.getAchievement());
		e.setCompleted(c.isCompleted());
		e.setDescription(c.getDescription());
		e.setStartdate(c.getStartdate());
		e.setEnddate(c.getEnddate());
		e.setProgress(c.getProgress());
		e.setMaxProgress(c.getMaxProgress());

		return repo.save(e);
	}

	public String deleteChallenge(int id) {
		ChallengeEntity c = repo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Challenge " + id + "does not exist"));

		if (c.isDeleted()) {
			return "Challenge #" + id + " is already deleted!";
		} else {
			c.setDeleted(true);
			repo.save(c);
			return "Challenge #" + id + "has been deleted";
		}
	}
}
