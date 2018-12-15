package com.easyrestaurant.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easyrestaurant.model.Customer;
import com.easyrestaurant.repository.Customers;
import com.easyrestaurant.service.exception.ExistingRecordException;

@Service
public class CustomerService {

	@Autowired
	private Customers customers;
	
	@Transactional
	public void save(Customer customer) {
		
		Optional<Customer> optional = customers.findByDocument(customer.getDocument());
		
		if(optional.isPresent()) {
			throw new ExistingRecordException("Cliente já cadastrado.");
		}
		
		customers.save(customer);
	}
	
}
