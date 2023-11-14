package com.anq.LifePP.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;


@Entity
@Table(name="tblAchievement")
public class AchievementEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int achievementID;
	@Column(name="name")
	private String name;
	@Column(name="description")
	private String description;
	
	public AchievementEntity() {}

	public AchievementEntity(int achievementID, String name, String description) {
		super();
		this.achievementID = achievementID;
		this.name = name;
		this.description = description;
	}

	public int getAchievementID() {
		return achievementID;
	}

	public void setAchievementID(int achievementID) {
		this.achievementID = achievementID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
}