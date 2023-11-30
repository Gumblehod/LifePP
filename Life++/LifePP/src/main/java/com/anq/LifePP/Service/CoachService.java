package com.anq.LifePP.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anq.LifePP.Entity.ChallengeEntity;
import com.anq.LifePP.Entity.CoachEntity;
import com.anq.LifePP.Repository.CoachRepository;

@Service
public class CoachService {
	
	@Autowired
	CoachRepository repo;
	
	public CoachEntity insertCoach(CoachEntity e) {
		return repo.save(e);
	}
	
	public List<CoachEntity> getallCoach(){
		return repo.findAll();
	}
	
	public CoachEntity updateCoach(int id, CoachEntity c) {
		CoachEntity e = new CoachEntity();
			try {
				e = repo.findById(id).get();
				e.setEmail(c.getEmail());
				e.setName(c.getName());
			}
			catch(NoSuchElementException ex) {
				throw new NoSuchElementException("Coach "+id+"doesn't exist.");
			}finally {
				return repo.save(e);
			}
	}
	
	public String deleteCoach(int id) {
		String msg = "";
		
			if(repo.findById(id).get()!=null) {
				if(repo.findById(id).get().isDeleted()){
					msg = "Coach #" + id + " is already deleted!";
				}
				else{
				CoachEntity a = repo.findById(id).get();
				a.setDeleted(true);
				msg = "Coach #" + id + "has been deleted";
				repo.save(a);
				}
			}
			else {msg = "Coach #" + id + " doesn't exist";}
			
			return msg;
	}
}
