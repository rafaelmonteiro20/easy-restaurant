package com.easyrestaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easyrestaurant.model.Group;

public interface Groups extends JpaRepository<Group, Long> {

}
