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
	
	public List<ChallengeEntity> getallChallenge(){
		return repo.findAll();
	}
	
	public ChallengeEntity updateChallenge(int id, ChallengeEntity c) {
		ChallengeEntity e = new ChallengeEntity();
			try {
				e = repo.findById(id).get();
				e.setTitle(c.getTitle());
				e.setAchievement(c.getAchievement());
				e.setCompleted(c.isCompleted());
				e.setDescription(c.getDescription());
				e.setStartdate(e.getStartdate());
				e.setEnddate(c.getEnddate());
				e.setProgress(c.getProgress());
				e.setMaxProgress(c.getMaxProgress());
			}
			catch(NoSuchElementException ex) {
				throw new NoSuchElementException("Challenge "+id+"doesn't exist.");
			}finally {
				return repo.save(e);
			}
	}
	
	public String deleteChallenge(int id) {
		String msg = "";
		
			if(repo.findById(id).get()!=null) {
				repo.deleteById(id);
				msg = "Challenge " + id + "has been deleted";
			}
			else {msg = "Challenge " + id + "doesnt't exist";}
			
			return msg;
	}
}
