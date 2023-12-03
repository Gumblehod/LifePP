package com.anq.LifePP.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.List;

import javax.persistence.Column;


@Entity
@Table(name="tblCoach")
public class CoachEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int coachid;
	@Column(name="username")
	private String username;
	@Column(name="name")
	private String name;
	@Column(name="email")
	private String email;
	@Column(name="deleted")
	private boolean isDeleted = false;
	
	@OneToMany(mappedBy = "coach")
    private List<CourseEntity> courses;

    public List<CourseEntity> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseEntity> courses) {
        this.courses = courses;
    }

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public CoachEntity() {}
	
	public CoachEntity(int coachid, String username, String name, String email, boolean isDeleted,
			List<CourseEntity> courses) {
		super();
		this.coachid = coachid;
		this.username = username;
		this.name = name;
		this.email = email;
		this.isDeleted = isDeleted;
		this.courses = courses;
	}

	public int getCoachid() {
		return coachid;
	}

	public void setCoachid(int coachid) {
		this.coachid = coachid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getCoachID() {
		return coachid;
	}
	public void setCoachID(int coachID) {
		this.coachid = coachID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}