package br.edu.ifpb.projetoum.springbatch.model.service;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ArquivoServiceImpl implements ArquivoService{
	
	private final String FOLDER = "uploads";
	private final Path root = Paths.get(FOLDER);
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss-SSS");
	
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
		String renamed = renameFile(file);
		Path path = this.root.resolve(renamed);
		File filePath = path.toFile();
		try {
			if (filePath.exists()) {
				return null;
			}
			Files.copy(file.getInputStream(), path);
			return path.toAbsolutePath().toString();
		} catch (IOException exception) {
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
	
	private String now() {
		return LocalDateTime.now().format(formatter);
	}
	
	private String renameFile(MultipartFile file) {
		String filename = FileUtils.getFilename(file);
		String extension = FileUtils.getExtension(file);
		
		return MessageFormat.format("{0}-{1}{2}", filename, now(), extension.isEmpty() ? extension : ".".concat(extension));
	}

}
