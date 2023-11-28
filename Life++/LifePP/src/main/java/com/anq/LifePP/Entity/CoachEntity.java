package com.anq.LifePP.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;


@Entity
@Table(name="tblCoach")
public class CoachEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int coachid;
	@Column(name="name")
	private String name;
	@Column(name="email")
	private String email;
	@Column(name="deleted")
	private boolean isDeleted = false;
	
	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public CoachEntity() {}
	
	public CoachEntity(int coachID, String name, String email) {
		super();
		this.coachid = coachID;
		this.name = name;
		this.email = email;
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