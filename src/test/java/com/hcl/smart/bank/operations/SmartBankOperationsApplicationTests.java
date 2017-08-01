package com.hcl.smart.bank.operations;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.hcl.smart.bank.operations.controller.BankCustomerOperationsController;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SmartBankOperationsApplicationTests {
	
	//final String BASE_URL = "http://localhost:8486/";
	
	/*@Autowired
    private BankCustomerOperationsController controllerToTest;
	
	 private MockMvc mockMvc;

     @Before
     public void setup() {
         mockMvc = MockMvcBuilders.standaloneSetup(controllerToTest).build();
     }

     @Test
     public void testCustomerRegistration() throws Exception{
         //Mocking Controller
         controllerToTest = mock(BankCustomerOperationsController.class);

          this.mockMvc.perform(get("/smart/bank/customer/registration")
                  .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                  .andExpect(status().isOk())
                  .andExpect(content().contentType(MediaType.APPLICATION_JSON));
     }
     
     @Test
     public void testFindCustomerByAccountNumber() throws Exception{
         //Mocking Controller
         controllerToTest = mock(BankCustomerOperationsController.class);

          this.mockMvc.perform(get("/smart/bank/customer/findCustomerByAccountNumber")
                  .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                  .andExpect(status().isOk())
                  .andExpect(content().contentType(MediaType.APPLICATION_JSON));
     }
     
     @Test
     public void testUpdateCustomer() throws Exception{
         //Mocking Controller
         controllerToTest = mock(BankCustomerOperationsController.class);

          this.mockMvc.perform(get("/smart/bank/customer/updateCustomer")
                  .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                  .andExpect(status().isOk())
                  .andExpect(content().contentType(MediaType.APPLICATION_JSON));
     }
     
	@Test
	public void contextLoads() {
	}*/
}
