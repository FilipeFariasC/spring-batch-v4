package br.edu.ifpb.projetoum.springbatch.model.service;

import java.io.File;
import java.util.Objects;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
	
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
	
	
}
