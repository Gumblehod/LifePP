package com.anq.LifePP.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anq.LifePP.Entity.AchievementEntity;
import com.anq.LifePP.Entity.QuestEntity;
import com.anq.LifePP.Repository.AchievementRepository;
import com.anq.LifePP.Repository.QuestRepository;

@Service
public class QuestService {

	@Autowired
	QuestRepository repo;
	@Autowired
	AchievementRepository a;

	public QuestEntity insertQuest(QuestEntity e) {
		return repo.save(e);
	}

	public List<QuestEntity> getallQuest() {
		return repo.findAll();
	}

	public QuestEntity updateQuest(int id, QuestEntity c) {
		QuestEntity quest = repo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Quest " + id + " doesn't exist."));

		// Update properties if they are not null in the updatedQuest

		if (c.getTitle() != null) {
			quest.setTitle(c.getTitle());
		}

		if (c.getAchievement() != null) {
			quest.setAchievement(c.getAchievement());
		}

		if (c.getDescription() != null) {
			quest.setDescription(c.getDescription());
		}

		// Assuming 0 is not a valid value for progress
		if (c.getProgress() != 0) {
			quest.setProgress(c.getProgress());
		}

		if (c.getMaxProgress() != 0) {
			quest.setMaxProgress(c.getMaxProgress());
		}

		return repo.save(quest);
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

	public QuestEntity addAchievement(int qid, int aid) {
		AchievementEntity ar = a.findById(aid)
				.orElseThrow(() -> new NoSuchElementException("Achievement " + aid + "does not exist"));
		QuestEntity c = repo.findById(qid)
				.orElseThrow(() -> new NoSuchElementException("Quest " + qid + "does not exist"));

		c.setAchievement(ar);
		repo.save(c);
		return c;
	}
}
