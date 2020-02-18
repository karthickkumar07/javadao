package com.infy.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


//Strictly follow class diagram
@Entity
@Table(name="investor")
public class InvestorEntity {

	// Your code goes here
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer investorId;
	private String name;
	private Long contactNumber;
	private String emailId;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="investor_id")
	private List<InvestmentEntity> investmentEntities;
	public Integer getInvestorId() {
		return investorId;
	}
	public void setInvestorId(Integer investorId) {
		this.investorId = investorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public List<InvestmentEntity> getIventities() {
		return investmentEntities;
	}
	public void setIventities(List<InvestmentEntity> investmentEntities) {
		this.investmentEntities = investmentEntities;
	}
	
}
