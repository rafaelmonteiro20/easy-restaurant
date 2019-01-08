package com.easyrestaurant.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.easyrestaurant.model.Customer;
import com.easyrestaurant.repository.filter.CustomerFilter;

public interface CustomersQueries {

	Page<Customer> findAll(CustomerFilter filter, Pageable pageable);
	
	Long countAll(CustomerFilter filter);
	
}
