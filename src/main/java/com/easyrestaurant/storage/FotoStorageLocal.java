package com.easyrestaurant.storage;

import static java.nio.file.FileSystems.getDefault;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

public class FotoStorageLocal implements FotoStorage {

	private static final Logger LOGGER = LoggerFactory.getLogger(FotoStorageLocal.class);
	
	private static final String DEFAULT_DIRECTORY_NAME = ".easyfotos";
	
	private static final String TEMPORARY_DIRECTORY = "temp";
	
	private Path local;
	
	private Path localTemporario;
	
	public FotoStorageLocal() {
		this(getDefault().getPath(System.getenv("HOME"), DEFAULT_DIRECTORY_NAME));
	}
	
	public FotoStorageLocal(Path path) {
		this.local = path;
		createDirectories();
	}

	@Override
	public String salvarTemporariamente(MultipartFile[] files) {
		if(isEmpty(files))
			return null;
		
		MultipartFile arquivo = files[0];
		String novoNome = renameFile(arquivo.getOriginalFilename());
			
		try {
			arquivo.transferTo(createFile(novoNome));
			return novoNome;
		} catch (IOException e) {
			throw new RuntimeException("Erro salvando a foto na pasta temporária", e);
		}
		
	}
	
	@Override
	public void salvar(String foto) {
		try {
			Files.move(this.localTemporario.resolve(foto), this.local.resolve(foto));
		} catch (IOException e) {
			throw new RuntimeException("Erro movendo a foto para destino final", e);
		}
		
		resize(foto);
	}
	
	@Override
	public byte[] recuperarFoto(String nome) {
		return recuperar(this.local.resolve(nome));
	}
	
	@Override
	public byte[] recuperarFotoTemporaria(String nome) {
		return recuperar(this.localTemporario.resolve(nome));
	}
	
	private byte[] recuperar(Path name) {
		try {
			return Files.readAllBytes(name);
		} catch (IOException e) {
			throw new RuntimeException("Erro ao ler a foto", e);
		}
	}

	private File createFile(String nome) {
		return new File(this.localTemporario.toAbsolutePath().toString() + getDefault().getSeparator() + nome);
	}
	
	private boolean isEmpty(MultipartFile[] files) {
		return files == null || files.length < 1;
	}
	
	private void createDirectories() {
		try {
			Files.createDirectories(this.local);
			this.localTemporario = getDefault().getPath(this.local.toString(), TEMPORARY_DIRECTORY);
			Files.createDirectories(this.localTemporario);
			
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Pastas criadas com sucesso.");
				LOGGER.debug("Pasta default: " + this.local.toAbsolutePath());
				LOGGER.debug("Pasta temporária: " + this.localTemporario.toAbsolutePath());
			}
		} catch (IOException e) {
			throw new RuntimeException("Erro ao criar pasta para salvar fotos", e);
		}
	}
	
	private String renameFile(String nomeOriginal) {
		String nome = UUID.randomUUID().toString() + "_" + nomeOriginal;
		
		if (LOGGER.isDebugEnabled())
			LOGGER.debug(String.format("Nome original: %s, novo nome: %s", nomeOriginal, nome));
		
		return nome;
	}
	
	private void resize(String foto) {
		try {
			Thumbnails.of(this.local.resolve(foto).toString()).size(50, 85).toFiles(Rename.PREFIX_DOT_THUMBNAIL);
		} catch (IOException e) {
			throw new RuntimeException("Erro gerando thumbnail", e);
		}
	}
	
}