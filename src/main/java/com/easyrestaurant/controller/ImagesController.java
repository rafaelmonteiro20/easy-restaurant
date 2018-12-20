package com.easyrestaurant.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.easyrestaurant.dto.ImageDTO;
import com.easyrestaurant.storage.ImageStorage;
import com.easyrestaurant.storage.ImageStorageRunnable;

@RestController
@RequestMapping("/images")
public class ImagesController {
	
	@Autowired
	private ImageStorage storage;

	@PostMapping
	public DeferredResult<ImageDTO> upload(@RequestParam("files[]") MultipartFile[] files) {
		DeferredResult<ImageDTO> result = new DeferredResult<>();
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(new ImageStorageRunnable(files, result, storage));
		executor.shutdown();
		
		return result;
	}
	
	@GetMapping("/temp/{name:.*}")
	public byte[] findTemporaryImage(@PathVariable String name) {
		return storage.getTemporaryImage(name);
	}
	
	@GetMapping("/{name:.*}")
	public byte[] findImage(@PathVariable String name) {
		return storage.getImage(name);
	}
	
}
