package com.hcl.smart.bank.operations.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@Table(name="customer")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CUSTOMER_ID", unique=true, nullable=false)
	private int customerId;

	@Column(name="CREATED_BY", length=45)
	private String createdBy;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="EMAIL_ID", length=45)
	private String emailId;

	@Column(name="FIRST_NAME", length=45)
	private String firstName;

	@Column(name="LAST_NAME", length=45)
	private String lastName;

	@Column(name="LOGIN_ID", length=45)
	private String loginId;

	@Column(name="LOGIN_PASSWORD", length=45)
	private String loginPassword;

	@Column(name="MOBILE_NUMBER", length=45)
	private String mobileNumber;

	@Column(name="MODIFIED_BY", length=45)
	private String modifiedBy;

	@Temporal(TemporalType.DATE)
	@Column(name="MODIFIED_DATE")
	private Date modifiedDate;

	@Column(name="NATIONAL_ID", length=45)
	private String nationalId;

	@Column(length=45)
	private String title;

	//bi-directional many-to-one association to CustomerAccount
	//@OneToMany(mappedBy="customer")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
	private List<CustomerAccount> customerAccounts;

	//bi-directional many-to-one association to CustomerAddress
	//@OneToMany(mappedBy="customer")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
	private List<CustomerAddress> customerAddresses;

	public Customer() {
	}

	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLoginId() {
		return this.loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginPassword() {
		return this.loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getNationalId() {
		return this.nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<CustomerAccount> getCustomerAccounts() {
		return this.customerAccounts;
	}

	public void setCustomerAccounts(List<CustomerAccount> customerAccounts) {
		this.customerAccounts = customerAccounts;
	}

	public CustomerAccount addCustomerAccount(CustomerAccount customerAccount) {
		getCustomerAccounts().add(customerAccount);
		customerAccount.setCustomer(this);

		return customerAccount;
	}

	public CustomerAccount removeCustomerAccount(CustomerAccount customerAccount) {
		getCustomerAccounts().remove(customerAccount);
		customerAccount.setCustomer(null);

		return customerAccount;
	}

	public List<CustomerAddress> getCustomerAddresses() {
		return this.customerAddresses;
	}

	public void setCustomerAddresses(List<CustomerAddress> customerAddresses) {
		this.customerAddresses = customerAddresses;
	}

	public CustomerAddress addCustomerAddress(CustomerAddress customerAddress) {
		getCustomerAddresses().add(customerAddress);
		customerAddress.setCustomer(this);

		return customerAddress;
	}

	public CustomerAddress removeCustomerAddress(CustomerAddress customerAddress) {
		getCustomerAddresses().remove(customerAddress);
		customerAddress.setCustomer(null);

		return customerAddress;
	}

}