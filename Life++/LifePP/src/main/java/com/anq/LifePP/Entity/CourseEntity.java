package com.anq.LifePP.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

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

	@ManyToMany(mappedBy = "joinedCourses")
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

	public CourseEntity(int courseID, String name, int max) {
		super();
		this.courseID = courseID;
		this.name = name;
		this.max = max;
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
}
