package com.easyrestaurant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easyrestaurant.model.Customer;
import com.easyrestaurant.repository.query.CustomersQueries;

public interface Customers extends JpaRepository<Customer, Long>, CustomersQueries {

	Optional<Customer> findByDocument(String document);

	List<Customer> findByNameStartingWithIgnoreCase(String name);

}
