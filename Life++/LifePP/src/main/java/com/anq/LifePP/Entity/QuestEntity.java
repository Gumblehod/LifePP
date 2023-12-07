package com.anq.LifePP.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;

@Entity
@Table(name = "tblQuest")
public class QuestEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int qid;
	@Column(name = "title")
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name = "progress")
	private int progress;
	@Column(name = "maxprogress")
	private int maxProgress;
	@Column(name = "completion")
	private boolean isCompleted;
	@Column(name = "deleted")
	private boolean isDeleted = false;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "achievement_id")
	private AchievementEntity achievement;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	@JsonBackReference
	private CourseEntity course;

	public AchievementEntity getAchievement() {
		return achievement;
	}

	public void setAchievement(AchievementEntity achievement) {
		this.achievement = achievement;
	}

	public CourseEntity getCourse() {
		return course;
	}

	public void setCourse(CourseEntity course) {
		this.course = course;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public QuestEntity() {
	}

	public QuestEntity(int qid, String title, String description, int progress, int maxProgress, boolean isCompleted) {
		super();
		this.qid = qid;
		this.title = title;
		this.description = description;
		this.progress = progress;
		this.maxProgress = maxProgress;
		this.isCompleted = isCompleted;
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
}
