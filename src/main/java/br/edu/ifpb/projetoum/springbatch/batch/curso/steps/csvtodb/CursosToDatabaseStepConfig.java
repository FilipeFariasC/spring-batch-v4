package br.edu.ifpb.projetoum.springbatch.batch.curso.steps.csvtodb;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.edu.ifpb.projetoum.springbatch.model.entity.CursoIfpb;

@Configuration
public class CursosToDatabaseStepConfig {

	private final StepBuilderFactory stepBuilderFactory;
	
	public CursosToDatabaseStepConfig(
			StepBuilderFactory stepBuilderFactory
		) {
		super();
		this.stepBuilderFactory = stepBuilderFactory;
	}

	
	@Bean("cursosIfpbStepChunk")
	public Step cursosIfpbStepChunk(
			@Qualifier("csvCurso")
			ItemReader<CursoIfpb> reader,
			@Qualifier("cursoToDatabaseWriter")
			ItemWriter<CursoIfpb> writer
			) {
		return stepBuilderFactory
			.get("cursosIfpbStepChunk")
			.<CursoIfpb, CursoIfpb>chunk(1)
			.reader(reader)
			.writer(writer)
			.build();
	}
}
