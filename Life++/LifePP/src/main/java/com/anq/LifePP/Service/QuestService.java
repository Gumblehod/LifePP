package com.anq.LifePP.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anq.LifePP.Entity.QuestEntity;
import com.anq.LifePP.Repository.QuestRepository;

@Service
public class QuestService {

	@Autowired
	QuestRepository repo;

	public QuestEntity insertQuest(QuestEntity e) {
		return repo.save(e);
	}

	public List<QuestEntity> getallQuest() {
		return repo.findAll();
	}

	public QuestEntity updateQuest(int id, QuestEntity c) {
		QuestEntity e = repo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Quest " + id + "doesn't exist."));

		e.setTitle(c.getTitle());
		e.setAchievement(c.getAchievement());
		e.setCompleted(c.isCompleted());
		e.setDescription(c.getDescription());
		e.setMaxProgress(c.getProgress());
		e.setProgress(c.getProgress());

		return repo.save(e);
	}

	public String deleteQuest(int id) {
		QuestEntity c = repo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Quest " + id + "does not exist"));

		if (c.isDeleted()) {
			return "Quest #" + id + " is already deleted!";
		} else {
			c.setDeleted(true);
			repo.save(c);
			return "Quest #" + id + "has been deleted";
		}
	}
}
