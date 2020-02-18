package com.infy.dao;


import java.util.ArrayList;
import java.util.List;





//import javax.management.Query;
import javax.persistence.EntityManager;



import javax.persistence.PersistenceContext;



import javax.persistence.Query;

import org.springframework.stereotype.Repository;





import com.infy.entity.InvestmentEntity;
import com.infy.entity.InvestorEntity;
import com.infy.model.Investor;
import com.infy.model.Investment;

@Repository(value="smartInvestmentDAO")
public class SmartInvestmentDAOImpl implements SmartInvestmentDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	// DO NOT CHANGE METHOD SIGNATURE AND DELETE/COMMENT METHOD
	/*This Method is used to check whether an investment is present in database or not?
	If present return status of investment else return '0'*/
	@Override
	public Character checkInvestment(Integer investmentId) {
	
		// Your code goes here
		InvestmentEntity investmentEntity=entityManager.find(InvestmentEntity.class, investmentId);
		if(investmentEntity==null){
			return '0';
		}else{
			return investmentEntity.getStatus();
		}
				

	}
	@Override
	public boolean checkInvestor(Integer investorId) {
		// TODO Auto-generated method stub
		InvestorEntity investorEntity=entityManager.find(InvestorEntity.class, investorId);
		if(investorEntity==null){
			return false;
		}else{
			return true;
		}
	}
	
	
		// DO NOT CHANGE METHOD SIGNATURE AND DELETE/COMMENT METHOD
		/*This Method is used to register an investor with one or more investment and also set the status 
		 of all investmentEntity as 'A' before registering .
		 On SuccessFull Register return investor id generated
		*/

	@Override
	public Integer registerInvestor(Investor investor) {
		// Your code goes here
		InvestorEntity investorEntity=new InvestorEntity();
		investorEntity.setContactNumber(investor.getContactNumber());
		investorEntity.setEmailId(investor.getEmailId());
		investorEntity.setName(investor.getName());
		List<InvestmentEntity> investmentEntities=new ArrayList<>();
		List<Investment> investments=investor.getInvestmentList();
		for(Investment investment:investments){
			InvestmentEntity investmentEntity=entityManager.find(InvestmentEntity.class, investment.getInvestmentId());
			investmentEntity.setStatus('A');
			investmentEntities.add(investmentEntity);
		}
		entityManager.persist(investorEntity);
		investorEntity.setIventities(investmentEntities);
		return investorEntity.getInvestorId();
	}
	
	
	// DO NOT CHANGE METHOD SIGNATURE AND DELETE/COMMENT METHOD
	
		/* This method is used to retrieve the investment details based on the status
		  If details found, list of investment will be returned, else an empty list will be returned */
	@Override
	public List<Investment> getInvestmentDetails(Character status) throws Exception {
		// Your code goes here
//		String query="SELECT c.investmentEntities FROM InvestorEntity c";
		String query="SELECT c FROM InvestorEntity c";
		Query q=entityManager.createQuery(query);
		System.out.println("Status Request:"+status);
		List<InvestorEntity> investorEntities=q.getResultList();
		List<Investment> investments=new ArrayList<Investment>();
		for(InvestorEntity investorEntity:investorEntities){
			List<InvestmentEntity> investmentEntities=investorEntity.getIventities();
			for(InvestmentEntity investmentEntity:investmentEntities){
				if(investmentEntity.getStatus()==status){
				Investment investment=new Investment();
				investment.setCategory(investmentEntity.getCategory());
				investment.setInvestmentName(investmentEntity.getInvestmentName());
				investment.setInvestmentId(investmentEntity.getInvestmentId());
				investment.setStatus(investmentEntity.getStatus());
				investments.add(investment);
				
				}
			}
			
		}
		
	
				return investments;
	}


	@Override
	public Integer updateInvestor(Integer investorId, String emailId)
			throws Exception {
		// TODO Auto-generated method stub
		if(checkInvestor(investorId)==false){
			throw new Exception("API.INVESTOR_NOT");
		}else{
			InvestorEntity investorEntity=entityManager.find(InvestorEntity.class, investorId);
			investorEntity.setEmailId(emailId);
			//entityManager.persist(investorEntity); no need to persist
			return investorEntity.getInvestorId();
		}
		
	}


	@Override
	public Integer deleteInvestor(Integer investorId) throws Exception {
		// TODO Auto-generated method stub
		if(checkInvestor(investorId)==false){
			throw new Exception("API.INVESTOR_NOT");
		}else{
			InvestorEntity investorEntity=entityManager.find(InvestorEntity.class, investorId);
			investorEntity.setIventities(null);
			entityManager.remove(investorEntity);
			return investorEntity.getInvestorId();
		}
		
	}




}
