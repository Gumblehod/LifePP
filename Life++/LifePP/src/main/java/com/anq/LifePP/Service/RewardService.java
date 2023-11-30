package com.anq.LifePP.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anq.LifePP.Entity.RewardEntity;
import com.anq.LifePP.Repository.RewardRepository;

@Service
public class RewardService {

	@Autowired
	RewardRepository repo;

	public RewardEntity insertReward(RewardEntity e) {
		return repo.save(e);
	}

	public List<RewardEntity> getallReward() {
		return repo.findAll();
	}

	public RewardEntity updateReward(int id, RewardEntity c) {
		RewardEntity e = repo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Reward " + id + "doesn't exist."));

		e.setName(c.getName());
		e.setQuantity(c.getQuantity());

		return repo.save(e);
	}

	public String deleteReward(int id) {
		RewardEntity c = repo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Reward " + id + "does not exist"));

		if (c.isDeleted()) {
			return "Reward #" + id + " is already deleted!";
		} else {
			c.setDeleted(true);
			repo.save(c);
			return "Reward #" + id + "has been deleted";
		}
	}
}
