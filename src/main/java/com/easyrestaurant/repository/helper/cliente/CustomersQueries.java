package com.easyrestaurant.repository.helper.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.easyrestaurant.model.Customer;
import com.easyrestaurant.repository.filter.CustomerFilter;

public interface CustomersQueries {

	Page<Customer> findAll(CustomerFilter filter, Pageable pageable);
	
}
