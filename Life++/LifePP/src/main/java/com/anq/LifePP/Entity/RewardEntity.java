package com.anq.LifePP.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;


@Entity
@Table(name="tblReward")
public class RewardEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int rid;
	@Column(name="name")
	private String name;
	@Column(name="quantity")
	private int points;
	@Column(name="deleted")
	private boolean isDeleted = false;
	

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public RewardEntity() {}
	
	public RewardEntity(int rid, String name, int quantity) {
		super();
		this.rid = rid;
		this.name = name;
		this.points = quantity;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return points;
	}
	public void setQuantity(int quantity) {
		this.points = quantity;
	}
	
	
}