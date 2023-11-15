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
	
	public List<RewardEntity> getallReward(){
		return repo.findAll();
	}
	
	public RewardEntity updateReward(int id, RewardEntity c) {
		RewardEntity e = new RewardEntity();
			try {
				e = repo.findById(id).get();
				e.setName(c.getName());
				e.setQuantity(c.getQuantity());
			}
			catch(NoSuchElementException ex) {
				throw new NoSuchElementException("Reward "+id+"doesn't exist.");
			}finally {
				return repo.save(e);
			}
	}
	
	public String deleteReward(int id) {
		String msg = "";
		
			if(repo.findById(id).get()!=null) {
				repo.deleteById(id);
				msg = "Reward " + id + "has been deleted";
			}
			else {msg = "Reward " + id + "doesnt't exist";}
			
			return msg;
	}
}
