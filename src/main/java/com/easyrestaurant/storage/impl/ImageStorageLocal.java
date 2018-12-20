package com.easyrestaurant.storage.impl;

import static java.nio.file.FileSystems.getDefault;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.easyrestaurant.storage.ImageStorage;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

public class ImageStorageLocal implements ImageStorage {

	private static final Logger logger = LoggerFactory.getLogger(ImageStorageLocal.class);
	
	private static final String DEFAULT_DIRECTORY_NAME = ".easy-images";
	
	private static final String TEMPORARY_DIRECTORY = "temp";
	
	private Path path;
	
	private Path pathTemp;
	
	public ImageStorageLocal() {
		this(getDefault().getPath(System.getenv("HOME"), DEFAULT_DIRECTORY_NAME));
	}
	
	public ImageStorageLocal(Path path) {
		this.path = path;
		this.createDirectories();
	}

	@Override
	public String saveTemp(MultipartFile[] files) {
		if(this.isEmpty(files)) {
			return null;
		}
		
		MultipartFile file = files[0];
		String name = renameFile(file.getOriginalFilename());
			
		try {
			file.transferTo(createFile(name));
			return name;
		} catch (IOException e) {
			throw new RuntimeException("Erro salvando a foto na pasta temporária", e);
		}
	}
	
	@Override
	public void save(String image) {
		try {
			Files.move(this.pathTemp.resolve(image), this.path.resolve(image));
		} catch (IOException e) {
			throw new RuntimeException("Error moving photo to final destination", e);
		}
		
//		resize(image);
	}
	
	@Override
	public byte[] getImage(String name) {
		return readImage(this.path.resolve(name));
	}
	
	@Override
	public byte[] getTemporaryImage(String name) {
		return readImage(this.pathTemp.resolve(name));
	}
	
	private byte[] readImage(Path name) {
		try {
			return Files.readAllBytes(name);
		} catch (IOException e) {
			throw new RuntimeException("Error reading image.", e);
		}
	}

	private File createFile(String name) {
		return new File(this.pathTemp.toAbsolutePath().toString() + getDefault().getSeparator() + name);
	}
	
	private boolean isEmpty(MultipartFile[] files) {
		return files == null || files.length < 1;
	}
	
	private void createDirectories() {
		try {
			Files.createDirectories(this.path);
			this.pathTemp = getDefault().getPath(this.path.toString(), TEMPORARY_DIRECTORY);
			Files.createDirectories(this.pathTemp);
			
			if (logger.isDebugEnabled()) {
				logger.debug("Folders created successfully.");
				logger.debug("Default folder: " + this.path.toAbsolutePath());
				logger.debug("Temp folder: " + this.pathTemp.toAbsolutePath());
			}
			
		} catch (IOException e) {
			throw new RuntimeException("Error creating folder to save photos.", e);
		}
	}
	
	private String renameFile(String originalName) {
		String name = UUID.randomUUID().toString() + "_" + originalName;
		
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Original name: %s, new name: %s", originalName, name));
		}
		
		return name;
	}
	
//	private void resize(String foto) {
//		try {
//			Thumbnails.of(this.local.resolve(foto).toString()).size(50, 85).toFiles(Rename.PREFIX_DOT_THUMBNAIL);
//		} catch (IOException e) {
//			throw new RuntimeException("Erro gerando thumbnail", e);
//		}
//	}
	
}
