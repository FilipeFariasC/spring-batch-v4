package br.edu.ifpb.projetoum.springbatch.model.service;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ArquivoServiceImpl implements ArquivoService{
	
	private final String FOLDER = "uploads";
	private final Path root = Paths.get(FOLDER);
	
	{
		this.init();
	}
	
	@Override
	public void init() {
		try {
			Files.createDirectories(root);
		} catch (IOException exception) {
			throw new UncheckedIOException(exception);
		}
	}
	
	@Override
	public String save(MultipartFile file) {
		Path path = this.root.resolve(file.getOriginalFilename());
		File filePath = path.toFile();
		try {
			if (filePath.exists()) {
				System.out.println("O arquivo existe");
				return null;
			}
			Files.copy(file.getInputStream(), path);
			return path.toAbsolutePath().toString();
		} catch (IOException exception) {
			System.out.println(file);
			throw new UncheckedIOException(exception);
		}
	}

	@Override
	public Resource load(String filename) {
		try {
			Path path = this.root.resolve(filename);
			Resource resource = new UrlResource(path.toUri());
			
			if (resource.exists() && resource.isReadable()) return resource;
			
			return null;
		} catch (MalformedURLException exception) {
			System.out.println(filename);
			throw new UncheckedIOException(exception);
		}
	}

	@Override
	public void delete(String filename) {
		try {
			Path path = this.root.resolve(filename);

			Files.delete(path);
		} catch (IOException exception) {
			throw new UncheckedIOException(exception);
		}
	}

}
