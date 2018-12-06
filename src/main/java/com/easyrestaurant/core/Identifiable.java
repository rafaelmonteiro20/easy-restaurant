package com.easyrestaurant.core;

import java.io.Serializable;

public interface Identifiable<T extends Serializable> {

	T getId();
	
	default boolean isNew() {
		return getId() == null;
	}
	
}
