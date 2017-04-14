package com.rm.easyrestaurant.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.rm.easyrestaurant.dto.FotoDTO;

public class FotoStorageRunnable implements Runnable {

	private MultipartFile[] files;
	private DeferredResult<FotoDTO> result;

	public FotoStorageRunnable(MultipartFile[] files, DeferredResult<FotoDTO> result) {
		this.files = files;
		this.result = result;
	}

	@Override
	public void run() {
		System.out.println("Salvando a foto " + files[0].getSize());
		
		String nome = files[0].getOriginalFilename();
		String contentType = files[0].getContentType();
		
		result.setResult(new FotoDTO(nome, contentType));
	}

}
