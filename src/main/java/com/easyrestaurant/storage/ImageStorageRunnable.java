package com.easyrestaurant.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.easyrestaurant.dto.ImageDTO;

public class ImageStorageRunnable implements Runnable {

	private MultipartFile[] files;
	private DeferredResult<ImageDTO> result;
	private ImageStorage storage;

	public ImageStorageRunnable(MultipartFile[] files, DeferredResult<ImageDTO> result, ImageStorage storage) {
		this.files = files;
		this.result = result;
		this.storage = storage;
	}

	@Override
	public void run() {
		String name = storage.saveTemp(files);
		result.setResult(new ImageDTO(name));
	}

}
