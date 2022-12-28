package br.edu.ifpb.projetoum.springbatch.curso.steps.dbtojson.writer;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;

import br.edu.ifpb.projetoum.springbatch.model.entity.CursoIfpbReduced;
import br.edu.ifpb.projetoum.springbatch.model.service.FileUtils;

@Configuration
public class CursosIfpbReducedToJsonWriterConfiguration {
	
	

	@Bean("cursoIfpbReducedToJsonWriter")
	@StepScope
	public JsonFileItemWriter<CursoIfpbReduced> cursoReducedJsonWriter(@Value("#{jobParameters['arquivo']}") String path) {
		JacksonJsonObjectMarshaller<CursoIfpbReduced> marshaller = new JacksonJsonObjectMarshaller<>();
		
		return new JsonFileItemWriterBuilder<CursoIfpbReduced>()
				.name("cursoIfpbReducedToJsonWriter")
				.jsonObjectMarshaller(marshaller)
				.encoding(StandardCharsets.UTF_8.displayName(new Locale("pt", "BR")))
				.resource(toResource(path))
				.build();
	}

	private Resource toResource(String filePath) {
		String filename = Paths.get(filePath).getFileName().toString().replaceAll(".\\w+$", FileUtils.Extension.JSON.getExtension());
		Path path = FileUtils.RESULT_PATH.resolve(filename);
		Resource resource = new PathResource(path);

		try {
			File file = resource.getFile();
			if (file.exists()) {
			    Files.delete(path);
			}
			Files.createFile(path);
		} catch (IOException exception) {
			exception.printStackTrace();
		}

		return resource;
	}
}
