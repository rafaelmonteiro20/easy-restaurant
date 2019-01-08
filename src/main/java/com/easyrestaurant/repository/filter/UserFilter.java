package com.easyrestaurant.repository.filter;

import java.util.List;

import com.easyrestaurant.model.Group;

public class UserFilter {

	private String name;
	private List<Group> groups;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGrupos(List<Group> groups) {
		this.groups = groups;
	}
	
	public boolean hasGroup() {
		return groups != null && !groups.isEmpty();
	}
	
}
