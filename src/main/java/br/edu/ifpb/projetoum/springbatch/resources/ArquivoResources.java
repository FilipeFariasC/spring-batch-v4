package br.edu.ifpb.projetoum.springbatch.resources;

import java.io.UncheckedIOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.edu.ifpb.projetoum.springbatch.model.service.ArquivoService;

@RestController
@RequestMapping("/arquivo")
public class ArquivoResources {
	
	private final ArquivoService arquivoService;
	
	public ArquivoResources(final ArquivoService arquivoService) {
		this.arquivoService = arquivoService;
	}
	
	
	@PostMapping("/upload")
	public ResponseEntity<ArquivoResponse> upload(@RequestParam("arquivo") MultipartFile file) {
		String caminho = arquivoService.save(file);
		return ResponseEntity.ok(ArquivoResponse.of(caminho));
	}
	
	@DeleteMapping("/delete/{filename}")
	public ResponseEntity<ArquivoResponse> upload(@PathVariable("filename") String filename) {
		try {
			arquivoService.delete(filename);
			return ResponseEntity.noContent().build();
		} catch (UncheckedIOException exception) {
			return ResponseEntity.notFound().build();
		}
	}
}
