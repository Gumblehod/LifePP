package com.anq.LifePP.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import java.util.ArrayList;
import java.util.List;

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
	@JsonIgnoreProperties("quests")
	private CourseEntity course;

	@ManyToMany(mappedBy = "ongoingQuests")
	@JsonIgnoreProperties({ "joinedCourses", "completedQuests", "course" }) // Updated to include "course"
	private List<UserEntity> ongoingUsers = new ArrayList<>();

	@ManyToMany(mappedBy = "completedQuests")
	@JsonIgnoreProperties({ "joinedCourses", "ongoingQuests", "course" }) // Updated to include "course"
	private List<UserEntity> completedByUsers = new ArrayList<>();

	public List<UserEntity> getCompletedByUsers() {
		return completedByUsers;
	}

	public List<UserEntity> getOngoingUsers() {
		return ongoingUsers;
	}

	public void setOngoingUsers(List<UserEntity> ongoingUsers) {
		this.ongoingUsers = ongoingUsers;
	}

	public void setCompletedByUsers(List<UserEntity> completedByUsers) {
		this.completedByUsers = completedByUsers;
	}

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
