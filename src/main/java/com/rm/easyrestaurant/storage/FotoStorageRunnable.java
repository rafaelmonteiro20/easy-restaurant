package com.rm.easyrestaurant.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.rm.easyrestaurant.dto.FotoDTO;

public class FotoStorageRunnable implements Runnable {

	private MultipartFile[] files;
	
	private DeferredResult<FotoDTO> result;
	
	private FotoStorage storage;

	public FotoStorageRunnable(MultipartFile[] files, DeferredResult<FotoDTO> result, FotoStorage storage) {
		this.files = files;
		this.result = result;
		this.storage = storage;
	}

	@Override
	public void run() {
		String nome = storage.salvarTemporariamente(files);
		String contentType = files[0].getContentType();
		
		result.setResult(new FotoDTO(nome, contentType));
	}

}