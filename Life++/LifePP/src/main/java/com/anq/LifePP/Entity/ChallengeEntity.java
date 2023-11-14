package com.anq.LifePP.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;


@Entity
@Table(name="tblChallenge")
public class ChallengeEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int cid;
	@Column(name="title")
    private String title;
	@Column(name="startdate")
	private String startdate;
	@Column(name="enddate")
	private String enddate;
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
	
	public ChallengeEntity() {}
	
	public ChallengeEntity(int cid, String title, String startdate, String enddate, String description, int progress,
			int maxProgress, boolean isCompleted, int achievement) {
		super();
		this.cid = cid;
		this.title = title;
		this.startdate = startdate;
		this.enddate = enddate;
		this.description = description;
		this.progress = progress;
		this.maxProgress = maxProgress;
		this.isCompleted = isCompleted;
		this.achievement = achievement;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
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
