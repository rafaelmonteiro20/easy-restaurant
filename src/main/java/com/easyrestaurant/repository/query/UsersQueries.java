package com.easyrestaurant.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.easyrestaurant.model.User;
import com.easyrestaurant.repository.filter.UserFilter;

public interface UsersQueries {

	Page<User> findAll(UserFilter filter, Pageable pageable);
	
	Long countAll(UserFilter filter);
	
}
