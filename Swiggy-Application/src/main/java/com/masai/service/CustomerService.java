package com.masai.service;

import java.util.List;

import com.masai.exception.CustomerException;
import com.masai.model.Customers;

public interface CustomerService {

	 public Customers addCustomer(Customers customer) throws CustomerException; 
	 
	 public List<Customers> getAllCustomers() throws CustomerException ;
	 
	 public Customers getCustomerById(int customerId) throws CustomerException;
}
