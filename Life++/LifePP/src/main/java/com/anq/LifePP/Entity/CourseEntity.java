package com.anq.LifePP.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;

@Entity
@Table(name = "tblCourse")
public class CourseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int courseID;
	@Column(name = "name")
	private String name;
	@Column(name = "max")
	private int max;
	@Column(name = "description")
	private String description;
	@Column(name = "participants")
	private int participants = 0;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coach_id")
	@JsonBackReference
	private CoachEntity coach;

	public CoachEntity getCoach() {
		return coach;
	}

	public void setCoach(CoachEntity coach) {
		this.coach = coach;
	}

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "course" })
	private List<QuestEntity> quests = new ArrayList<>();

	public List<QuestEntity> getQuests() {
		return quests;
	}

	public void setQuests(List<QuestEntity> quests) {
		this.quests = quests;
	}

	@ManyToMany
	@JoinTable(name = "user_course", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	@JsonIgnoreProperties({ "enrolledUsers", "quests", "joinedCourses", "ongoingQuests", "ongoingUsers",
			"completedQuests" }) // Updated to include "quests"
	private List<UserEntity> enrolledUsers = new ArrayList<>();

	public List<UserEntity> getEnrolledUsers() {
		return enrolledUsers;
	}

	public void setEnrolledUsers(List<UserEntity> enrolledUsers) {
		this.enrolledUsers = enrolledUsers;
	}

	@Column(name = "deleted")
	private boolean isDeleted = false;

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public CourseEntity() {
	}

	public CourseEntity(int courseID, String name, int max, String description, CoachEntity coach) {
		super();
		this.courseID = courseID;
		this.name = name;
		this.max = max;
		this.description = description;
		this.coach = coach;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getParticipants() {
		return participants;
	}

	public void setParticipants(int p) {
		this.participants = p;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String d) {
		this.description = d;
	}
}
