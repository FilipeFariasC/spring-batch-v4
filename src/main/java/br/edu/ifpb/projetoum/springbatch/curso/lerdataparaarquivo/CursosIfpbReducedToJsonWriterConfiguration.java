package br.edu.ifpb.projetoum.springbatch.batch.curso.shared.writer;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;

import br.edu.ifpb.projetoum.springbatch.model.CursoIfpbReduced;

@Configuration
public class CursosIfpbReducedToJsonWriterConfiguration {
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static final String BASE_FILENAME = "/cursos-%s.json";
	private static final String TEMP = "/tmp";
	
	@Bean("jsonCursoReducedWriter")
	public JsonFileItemWriter<CursoIfpbReduced> cursoReducedJsonWriter() {
		JacksonJsonObjectMarshaller<CursoIfpbReduced> marshaller = new JacksonJsonObjectMarshaller<>();
		return new JsonFileItemWriterBuilder<CursoIfpbReduced>()
				.name("jsonCursoReducedWriter")
				.jsonObjectMarshaller(marshaller)
				.encoding(StandardCharsets.UTF_8.displayName())
				.resource(toResource())
				.build();
	}
	
	private Resource toResource() {
		Resource resource = new PathResource(getPath());
		
		try {
			File file = resource.getFile();
			file.createNewFile();
		} catch (IOException exception) {
		}
		
		return resource;
	}
	
	private Path getPath() {
		return Paths.get(TEMP, getFilename()).toAbsolutePath();
	}
	
	private String getFilename() {
		return String.format(BASE_FILENAME, horarioAtual());
	}
	
	private String horarioAtual() {
		return LocalDateTime.now().format(formatter);
	}
}
