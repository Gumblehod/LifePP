package com.anq.LifePP.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

@Entity
@Table(name = "tblUser")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int userid;

	@Column(name = "username")
	private String username;
	@Column(name = "fname")
	private String fname;
	@Column(name = "lname")
	private String lname;
	@Column(name = "email")
	private String email;
	@Column(name = "pnum")
	private String pnum;
	@Column(name = "gender")
	private String gender;
	@Column(name = "birthdate")
	private String birthdate;
	@Column(name = "password")
	private String password;
	@Column(name = "type")
	private int type;
	@Column(name = "achievement_point")
	private int achievementPoint = 0;
	@ManyToMany
	@JoinTable(name = "user_reward", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "reward_id"))
	private List<RewardEntity> items = new ArrayList<>();

	public List<RewardEntity> getItems() {
		return items;
	}

	public void setItems(List<RewardEntity> items) {
		this.items = items;
	}

	@ManyToMany
	@JoinTable(name = "user_course", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	@JsonIgnoreProperties("enrolledUsers") // Ignore enrolledUsers attribute in CourseEntity
	private List<CourseEntity> joinedCourses = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "user_quest", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "quest_id"))
	@JsonIgnoreProperties({ "ongoingUsers", "completedByUsers", "quests" }) // Updated to include "quests"
	private List<QuestEntity> ongoingQuests = new ArrayList<>();

	public List<QuestEntity> getOngoingQuests() {
		return ongoingQuests;
	}

	public void setOngoingQuests(List<QuestEntity> ongoingQuests) {
		this.ongoingQuests = ongoingQuests;
	}

	@ManyToMany
	@JoinTable(name = "user_completed_quest", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "quest_id"))
	@JsonIgnoreProperties({ "enrolledUsers", "ongoingUsers" }) // Ignore these attributes in QuestEntity
	private List<QuestEntity> completedQuests = new ArrayList<>();

	public List<QuestEntity> getCompletedQuests() {
		return completedQuests;
	}

	public void setCompletedQuests(List<QuestEntity> completedQuests) {
		this.completedQuests = completedQuests;
	}

	@Column(name = "deleted")
	private boolean isDeleted = false;

	public List<CourseEntity> getJoinedCourses() {
		return joinedCourses;
	}

	public void setJoinedCourses(List<CourseEntity> joinedCourses) {
		this.joinedCourses = joinedCourses;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public UserEntity() {
	}

	public UserEntity(int userid, String username, String fname, String lname, String email, String pnum, String gender,
			String birthdate, String password, int type, boolean isDeleted) {
		super();
		this.userid = userid;
		this.username = username;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.pnum = pnum;
		this.gender = gender;
		this.birthdate = birthdate;
		this.password = password;
		this.type = type;
		this.isDeleted = isDeleted;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPnum() {
		return pnum;
	}

	public void setPnum(String pnum) {
		this.pnum = pnum;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getAchievementPoint() {
		return achievementPoint;
	}

	public void setAchievementPoint(int achievementPoint) {
		this.achievementPoint = achievementPoint;
	}
}
