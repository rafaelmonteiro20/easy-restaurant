package com.rm.easyrestaurant.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.rm.easyrestaurant.dto.FotoDTO;
import com.rm.easyrestaurant.storage.FotoStorageRunnable;

@RestController
@RequestMapping("/fotos")
public class FotosController {

	@PostMapping
	public DeferredResult<FotoDTO> upload(@RequestParam("files[]") MultipartFile[] files) {
		DeferredResult<FotoDTO> result = new DeferredResult<>();
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(new FotoStorageRunnable(files, result));
		executor.shutdown();
		
		return result;
	}
	
}