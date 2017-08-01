package com.hcl.smart.bank.operations.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hcl.smart.bank.operations.entity.Customer;
import com.hcl.smart.bank.operations.entity.CustomerAddress;
import com.hcl.smart.bank.operations.exception.MessageCodeEnum;
import com.hcl.smart.bank.operations.exception.SmartBankCustomerException;

@Transactional
@Repository
public class CustomerDAO implements ICustomerDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDAO.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void addCustomer(Customer customer) {
		LOGGER.info("In addCustomer method, adding the new customer");
		if (customer != null) {
			entityManager.persist(customer);
			entityManager.flush();
			LOGGER.info("In addCustomer method, successfully added new customer");
		}
	}

	@Override
	public Customer findCustomerByAccountNumber(Integer pCustomerAccountNumber) throws SmartBankCustomerException {
		LOGGER.info("In findCustomerByAccountNumber method, received the account number");
		Customer customer = new Customer();
		
		if(pCustomerAccountNumber != null) {
			TypedQuery<Customer> typedQuery = entityManager.createQuery("SELECT customer FROM Customer customer, CustomerAccount customer_account   WHERE customer_account.accountNumber = :pCustomerAccountNumber AND customer_account.customer.customerId = customer.customerId", Customer.class);
            typedQuery.setParameter("pCustomerAccountNumber", pCustomerAccountNumber);
            customer = typedQuery.getSingleResult();
            LOGGER.info("Received the customer object from database ");
			if(customer == null) {
				throw new SmartBankCustomerException(MessageCodeEnum.CUSTOMER_LOOKUP_FAILED, "Customer not found with that the provided account number. Please provide valid account number.");
			}
		}
		
		return customer;
	}
	
	@Override
	public Customer updateCustomer(Customer pCustomer) throws SmartBankCustomerException {
		LOGGER.info("In updateCustomer method, merging information into the database");
		Customer originalCustomer = null;
		
		//find the customer account in the ING DB and update required fields, then commit. For any issues roll-back changes.
		if(pCustomer != null && pCustomer.getCustomerAccounts() != null && !pCustomer.getCustomerAccounts().isEmpty()) {
			originalCustomer = findCustomerByAccountNumber(pCustomer.getCustomerAccounts().get(0).getAccountNumber());
			LOGGER.info("Found the record in the database with the provided customer account number");
		}
		
		if(pCustomer != null) {
			if(originalCustomer != null) {
				if(pCustomer.getFirstName() != null && !pCustomer.getFirstName().isEmpty()) {
					originalCustomer.setFirstName(pCustomer.getFirstName());
				}
				if(pCustomer.getLastName() != null && !pCustomer.getLastName().isEmpty()) {
					originalCustomer.setLastName(pCustomer.getLastName());
				}
				if(pCustomer.getMobileNumber() != null && !pCustomer.getMobileNumber().isEmpty()) {
					originalCustomer.setMobileNumber(pCustomer.getMobileNumber());
				}
				if(pCustomer.getNationalId() != null && !pCustomer.getNationalId().isEmpty()) {
					originalCustomer.setNationalId(pCustomer.getNationalId());
				}
				if(pCustomer.getTitle() != null && !pCustomer.getTitle().isEmpty()) {
					originalCustomer.setTitle(pCustomer.getTitle());
				}
				if(pCustomer.getLoginPassword() != null && !pCustomer.getLoginPassword().isEmpty()) {
					originalCustomer.setLoginPassword(pCustomer.getLoginPassword());
				}
				if(pCustomer.getLoginId() != null && !pCustomer.getLoginId().isEmpty()) {
					originalCustomer.setLoginId(pCustomer.getLoginId());
				}
				if(pCustomer.getEmailId() != null && !pCustomer.getEmailId().isEmpty()) {
					originalCustomer.setEmailId(pCustomer.getEmailId());
				}
				if(pCustomer.getCustomerAddresses() != null && !pCustomer.getCustomerAddresses().isEmpty()) {
					for (CustomerAddress address : pCustomer.getCustomerAddresses()) {
						originalCustomer.addCustomerAddress(address);
					}
				}
				
				entityManager.merge(originalCustomer);
				LOGGER.info("merged the customer details in the backend system");
			} else {
				throw new SmartBankCustomerException(MessageCodeEnum.UPDATE_TRANSACTION_FAILURE, "Customer details not found.");
			}
		}
		
		return originalCustomer;
	}
}