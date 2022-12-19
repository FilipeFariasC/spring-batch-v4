package br.edu.ifpb.projetoum.springbatch.curso.steps.xlsxtodb.reader;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import br.edu.ifpb.projetoum.springbatch.model.entity.CursoIfpb;

@Configuration
public class CursosXlsxToDatabaseStep {
	
	private final StepBuilderFactory stepBuilderFactory;
	
	public CursosXlsxToDatabaseStep(
			StepBuilderFactory stepBuilderFactory
		) {
		super();
		this.stepBuilderFactory = stepBuilderFactory;
	}

	
	@Bean("cursosIfpbXlsxParaDatabase")
	public Step cursosIfpbXlsxParaDatabase(
			@Qualifier("cursoXlsReader")
			ItemReader<CursoIfpb> reader,
			@Qualifier("cursoToDatabaseWriter")
			ItemWriter<CursoIfpb> writer
			) {
		return stepBuilderFactory
			.get("cursosIfpbXlsxParaDatabase")
			.<CursoIfpb, CursoIfpb>chunk(1)
			.reader(reader)
			.writer(writer)
			.build();
	}
}
