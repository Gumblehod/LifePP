package com.anq.LifePP.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anq.LifePP.Entity.ChallengeEntity;
import com.anq.LifePP.Entity.QuestEntity;
import com.anq.LifePP.Repository.QuestRepository;

@Service
public class QuestService {
	
	@Autowired
	QuestRepository repo;
	
	public QuestEntity insertQuest(QuestEntity e) {
		return repo.save(e);
	}
	
	public List<QuestEntity> getallQuest(){
		return repo.findAll();
	}
	
	public QuestEntity updateQuest(int id, QuestEntity c) {
		QuestEntity e = new QuestEntity();
			try {
				e = repo.findById(id).get();
				e.setTitle(e.getTitle());
				e.setAchievement(e.getAchievement());
				e.setCompleted(c.isCompleted());
				e.setDescription(c.getDescription());
				e.setMaxProgress(e.getProgress());
				e.setProgress(c.getProgress());
			}
			catch(NoSuchElementException ex) {
				throw new NoSuchElementException("Quest "+id+"doesn't exist.");
			}finally {
				return repo.save(e);
			}
	}
	
	public String deleteQuest(int id) {
		String msg = "";
		
			if(repo.findById(id).get()!=null) {
				if(repo.findById(id).get().isDeleted()){
					msg = "Quest #" + id + " is already deleted!";
				}
				else{
				QuestEntity a = repo.findById(id).get();
				a.setDeleted(true);
				msg = "Quest #" + id + "has been deleted";
				repo.save(a);
				}
			}
			else {msg = "Quest #" + id + " doesn't exist";}
			
			return msg;
	}
}
