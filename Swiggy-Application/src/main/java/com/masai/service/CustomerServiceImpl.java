package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CustomerException;
import com.masai.model.Customers;
import com.masai.repository.CustomerRepository;

@Service 
public class CustomerServiceImpl implements CustomerService {

	 @Autowired
	 private CustomerRepository customerrepository;

	@Override
	public Customers addCustomer(Customers customer) throws CustomerException {
		
		Customers addcustomer = customerrepository.save(customer);
		
		if(addcustomer!= null) {
			
			return addcustomer;
		}
		else {
			
			throw new CustomerException("There is no customer");
		}
		
	}
	
	
	public List<Customers> getAllCustomers() throws CustomerException {
        return customerrepository.findAll();
    }

    public Customers getCustomerById(int customerId) throws CustomerException {
        
    	return customerrepository.findById(customerId)
                .orElseThrow(() -> new CustomerException("Customer not found with ID: " + customerId));
    }


	
	 
	 
	 
	  
}
