package com.infy;




import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.infy.dao.SmartInvestmentDAO;
import com.infy.model.Investment;
import com.infy.model.Investor;
import com.infy.service.SmartInvestmentService;
import com.infy.service.SmartInvestmentServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmartInvestmentApplicationTests {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Mock
	private SmartInvestmentDAO smartInvestmentDAO;

	@InjectMocks
	private SmartInvestmentService smartInvestmentService = new SmartInvestmentServiceImpl();

	// DO NOT CHANGE METHOD SIGNATURE AND DELETE/COMMENT METHOD
	
	//This Method is used to test that an investor cannot register to investment with status as 'A'
	@Test
	public void registerInvestorInvestmentAlreadyActiveTest() throws Exception {
		expectedException.expect(Exception.class);
		expectedException.expectMessage("SERVICE.INVESTMENT_ALREADY_ACTIVE");
		Investor investor =new Investor();
		investor.setName("James");
		investor.setContactNumber(8984119985L);
		investor.setInvestorId(1);
		investor.setEmailId("james@123.com");
		List<Investment> investments=new ArrayList<Investment>();
		Investment investment=new Investment();
		investment.setInvestmentId(1015);
		investment.setCategory("Postal Schemes");
		investment.setStatus('A');//value already set a
		investment.setInvestmentName("Public Provident Fund");
		investments.add(investment);
		investor.setInvestmentList(investments);
		Mockito.when(smartInvestmentDAO.checkInvestment(investment.getInvestmentId())).thenReturn('A');
		smartInvestmentService.registerInvestor(investor);
		
	}

	//This Method is used to test that an investor cannot register to investment not present in database
	@Test
	public void registerInvestorInvestmentNotAvailableTest() throws Exception {

		expectedException.expect(Exception.class);
		expectedException.expectMessage("SERVICE.INVESTMENT_NOT_AVAILABLE");
		Investor investor =new Investor();
		investor.setName("James");
		investor.setContactNumber(8984119985L);
		investor.setInvestorId(1);
		investor.setEmailId("james@123.com");
		List<Investment> investments=new ArrayList<Investment>();
		Investment investment=new Investment();
		investment.setInvestmentId(1016);//invalid id
		investment.setCategory("Postal Schemes");
		investment.setStatus('A');
		investment.setInvestmentName("Public Provident Fund");
		investments.add(investment);
		investor.setInvestmentList(investments);
		Mockito.when(smartInvestmentDAO.checkInvestment(investment.getInvestmentId())).thenReturn('0');
		smartInvestmentService.registerInvestor(investor);
		

	}
}
