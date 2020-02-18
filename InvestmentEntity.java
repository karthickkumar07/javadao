package com.infy.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



//Strictly follow class diagram
@Entity
@Table(name="investment")
public class InvestmentEntity {
	// Your code goes here
	@Id
	private Integer investmentId;
	private String investmentName;
	private String category;
	private Character status;
	public Integer getInvestmentId() {
		return investmentId;
	}
	public void setInvestmentId(Integer investmentId) {
		this.investmentId = investmentId;
	}
	public String getInvestmentName() {
		return investmentName;
	}
	public void setInvestmentName(String investmentName) {
		this.investmentName = investmentName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Character getStatus() {
		return status;
	}
	public void setStatus(Character status) {
		this.status = status;
	}
	
}
