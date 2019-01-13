package com.easyrestaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.easyrestaurant.model.Permission;
import com.easyrestaurant.model.User;

public interface Permissions extends JpaRepository<Permission, Long> {

	@Query("select role from Permission")
	List<String> findAllRoles();
	
	@Query("select distinct p.role from User u "
		 + "inner join u.groups g "
		 + "inner join g.permissions p ")
	List<String> findByUser(User user);
	
}
