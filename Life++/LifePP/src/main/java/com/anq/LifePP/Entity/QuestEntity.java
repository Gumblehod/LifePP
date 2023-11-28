package com.anq.LifePP.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;


@Entity
@Table(name="tblQuest")
public class QuestEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int qid;
	@Column(name="title")
    private String title;
	@Column(name="description")
    private String description;
	@Column(name="progress")
    private int progress;
	@Column(name="maxprogress")
    private int maxProgress;
	@Column(name="completion")
    private boolean isCompleted;
	@Column(name="achievement")
    private int achievement;
	@Column(name="deleted")
	private boolean isDeleted = false;
	
	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public QuestEntity() {}

	public QuestEntity(int qid, String title, String description, int progress, int maxProgress, boolean isCompleted,
			int achievement) {
		super();
		this.qid = qid;
		this.title = title;
		this.description = description;
		this.progress = progress;
		this.maxProgress = maxProgress;
		this.isCompleted = isCompleted;
		this.achievement = achievement;
	}

	public int getQid() {
		return qid;
	}

	public void setQid(int qid) {
		this.qid = qid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public int getMaxProgress() {
		return maxProgress;
	}

	public void setMaxProgress(int maxProgress) {
		this.maxProgress = maxProgress;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public int getAchievement() {
		return achievement;
	}

	public void setAchievement(int achievement) {
		this.achievement = achievement;
	}
}
