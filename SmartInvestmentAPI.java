package com.infy.api;


import java.util.List;














import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;





import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.model.Investor;
import com.infy.model.Investment;
import com.infy.service.SmartInvestmentService;

@RestController
@RequestMapping(value="/investment")
@CrossOrigin(origins="http://localhost:4200")//for enabling cross orgin support while connecting to Angular 
public class SmartInvestmentAPI {
	@Autowired
	private SmartInvestmentService smartInvestmentService;
	@Autowired
	public Environment environment;

	// DO NOT CHANGE METHOD SIGNATURE AND DELETE/COMMENT METHOD

	/* This method calls service layer method to register a new investor with one or more than one investment .
	 If there is any violation, throws ResponseStatusException */
	@PostMapping(value="/investor")
	public ResponseEntity<String> registerInvestor( @RequestBody Investor investor) throws Exception {
		// Your code goes here
		try{
			Integer id=smartInvestmentService.registerInvestor(investor);
			ResponseEntity<String> response=new ResponseEntity<String>(environment.getProperty("API.REGISTRATION_SUCCESS")+id, HttpStatus.CREATED);
			return response;
		}catch(Exception exception){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(exception.getMessage()));
		}
		
	}

	// DO NOT CHANGE METHOD SIGNATURE AND DELETE/COMMENT METHOD

	/*
	  This method calls service layer method to get investment details based on
	 status. If there is any violation, throws ResponseStatusException
	 */
	@GetMapping(value="/{status}")
	public ResponseEntity<List<Investment>> getInvestementDetails(@PathVariable Character status) throws Exception {
		// Your code goes here
		try{
			List<Investment> investments=smartInvestmentService.getInvestmentDetails(status);
			ResponseEntity<List<Investment>> response=new ResponseEntity<List<Investment>>(investments, HttpStatus.OK);
			return response;
		}catch(Exception exception){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,environment.getProperty(exception.getMessage()));
		}

	}
	 @PutMapping(value = "/investor/{investorId}")
		public ResponseEntity<String> updateCustomer(@PathVariable Integer investorId, @RequestBody Investor investor)  throws Exception {
		try{	
			Integer id=smartInvestmentService.updateInvestor(investorId,investor.getEmailId());
			ResponseEntity<String> response = new ResponseEntity<String>("Investor "+id+" "+environment.getProperty("API.INVESTOR_UPDATED"), HttpStatus.OK);
			return response;
		}catch(Exception exception){
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,environment.getProperty(exception.getMessage()));
			
		}
		}
	 
	 @DeleteMapping(value = "/{investorId}")
		public ResponseEntity<String> deleteCustomer(@PathVariable Integer investorId) throws Exception  {
		 try{
			 Integer id=smartInvestmentService.deleteInvestor(investorId);
			ResponseEntity<String> response = new ResponseEntity<String>("Investor "+id+" "+environment.getProperty("API.INVESTOR_DELETE"), HttpStatus.OK);
			return response;
		 }catch(Exception exception){
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND,environment.getProperty(exception.getMessage()));
			}
		}
	 

}
