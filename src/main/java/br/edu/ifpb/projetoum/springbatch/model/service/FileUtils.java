package br.edu.ifpb.projetoum.springbatch.model.service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

	private static final String RESULT = "result";
	public static final Path RESULT_PATH = Paths.get(RESULT);

	private static final String UPLOAD = "uploads";
	public static final Path UPLOAD_PATH = Paths.get(UPLOAD);
	
	public enum Extension {
		CSV("csv"),
		JSON("json"),
		YAML("yml", "yaml");
		private String main;
		private List<String> others;
		Extension (String main, String... others) {
			this.main = main;
			this.others = Stream.of(others).collect(Collectors.toList());
			this.others.add(main);
		}
		public String getExtension() {
			return MessageFormat.format(".{0}", main);
		}
		public String[] getExtensions() {
			return others.stream().toArray(String[]::new);
		}
	}
	
	private FileUtils() {}
	
	public static String getExtension(File file) {
		if(Objects.isNull(file)) return "";

		return extensionOf(file.getName());
	}
	public static String getExtension(MultipartFile file) {
		if(Objects.isNull(file)) return "";
		
		return extensionOf(file.getOriginalFilename());
	}
	
	public static String getFilename(File file) {
		return fileNameOf(file.getName());
	}
	public static String getFilename(MultipartFile file) {
		if(Objects.isNull(file)) return "";
		
		return fileNameOf(file.getOriginalFilename());
	}
	
	private static String fileNameOf(String filename) {
		if(Objects.isNull(filename)) return "";
		
		int index = filename.lastIndexOf('.');
		if (index <= 0) 
			return filename.substring(index+1);
		
		return filename.substring(0, index);
	}
	private static String extensionOf(String filename) {
		if(Objects.isNull(filename)) return "";
		
		int index = filename.lastIndexOf('.');
		if (index <= 0) return "";
	
		return filename.substring(index+1);
	}
	
	public static String filename(String filename, String extension) {
		return MessageFormat.format("{0}-{1}{2}", filename, LocalDateTime.now().format(DateUtils.formatter), extension.isEmpty() ? extension : ".".concat(extension));
	}
	
	public static String filename(MultipartFile file) {
		String filename = getFilename(file);
		String extension = getExtension(file);
		
		return filename(filename, extension);
	}
	
	public static String replaceExtension(String filename, Extension extension) {
		String fileExtension = extensionOf(filename);
		if(fileExtension.isEmpty()) {
			return filename;
		}
		return filename.replace("."+fileExtension, extension.getExtension());
	}
	
}
