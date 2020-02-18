package com.infy.service;

import java.util.List;










import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.dao.SmartInvestmentDAO;
import com.infy.model.Investor;
import com.infy.model.Investment;
import com.infy.validator.Validator;


@Service(value="smartInvestmentService")
@Transactional
public class SmartInvestmentServiceImpl implements SmartInvestmentService {

	@Autowired
	private SmartInvestmentDAO smartInvestmentDAO;

	// DO NOT CHANGE METHOD SIGNATURE AND DELETE/COMMENT METHOD
	/* This method investment  details, checks whether the investment  exists or not,
	 * and validates the status of investment 
	 *If everything is fine, it calls DAO method to register new Investor to the database */
	@Override
	public Integer registerInvestor(Investor investor) throws Exception {
		// Your code goes here
		List<Investment> investments=investor.getInvestmentList();
		for(Investment investment:investments){
			Validator.validate(investment);
			Character status=smartInvestmentDAO.checkInvestment(investment.getInvestmentId());
			if(status=='A'){
				throw new Exception("SERVICE.INVESTMENT_ALREADY_ACTIVE");
			}else if(status=='0'){
				throw new Exception("SERVICE.INVESTMENT_NOT_AVAILABLE");
			}
		}
		return smartInvestmentDAO.registerInvestor(investor);

	}

	
	// DO NOT CHANGE METHOD SIGNATURE AND DELETE/COMMENT METHOD
	
		/* This method calls DAO method to retrieve the investnent details based on the status */
	@Override
	public List<Investment> getInvestmentDetails(Character status) throws Exception {
		// Your code goes here
		List<Investment> investments=smartInvestmentDAO.getInvestmentDetails(status);
		if(investments.isEmpty()){
			throw new Exception("SERVICE.NO_INVESTMENT_FOUND");
		}else{
			return investments;
		}
		
	}


	@Override
	public Integer updateInvestor(Integer investorId, String emailId)throws Exception {
		// TODO Auto-generated method stub
		return smartInvestmentDAO.updateInvestor(investorId,emailId);	
	}


	@Override
	public Integer deleteInvestor(Integer investorId) throws Exception {
		// TODO Auto-generated method stub
		return smartInvestmentDAO.deleteInvestor(investorId);
	}



	

}
