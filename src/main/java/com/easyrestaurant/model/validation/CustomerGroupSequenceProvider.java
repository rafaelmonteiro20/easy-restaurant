package com.easyrestaurant.model.validation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import com.easyrestaurant.model.Customer;

public class CustomerGroupSequenceProvider implements DefaultGroupSequenceProvider<Customer> {

	@Override
	public List<Class<?>> getValidationGroups(Customer customer) {
		
		List<Class<?>> groups = new ArrayList<>();
		groups.add(Customer.class);
		
		if (this.isSelectedCustomer(customer)) {
			groups.add(customer.getType().getGroup());
		}
		
		return groups;
	}

	private boolean isSelectedCustomer(Customer customer) {
		return customer != null && customer.getType() != null;
	}
	
}
