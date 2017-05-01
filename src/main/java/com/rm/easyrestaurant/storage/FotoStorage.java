package com.rm.easyrestaurant.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage {

	String salvarTemporariamente(MultipartFile[] files);
	
	void salvar(String nome);

	byte[] recuperarFotoTemporaria(String nome);

	byte[] recuperarFoto(String nome);

}