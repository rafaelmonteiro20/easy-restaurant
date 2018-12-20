package com.easyrestaurant.storage;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorage {

	String saveTemp(MultipartFile[] files);
	
	void save(String name);

	byte[] getTemporaryImage(String name);

	byte[] getImage(String name);

}
