package br.edu.ifpb.projetoum.springbatch.batch.curso.steps.csvtojson;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.edu.ifpb.projetoum.springbatch.model.CursoIfpb;
import br.edu.ifpb.projetoum.springbatch.model.CursoIfpbReduced;

@Configuration
public class CursosToJsonStepConfig {

	private final StepBuilderFactory stepBuilderFactory;
	
	public CursosToJsonStepConfig(
			StepBuilderFactory stepBuilderFactory
		) {
		super();
		this.stepBuilderFactory = stepBuilderFactory;
	}

	
	@Bean("cursosIfpbStepChunk")
	public Step cursosIfpbStepChunk(
			@Qualifier("csvCurso")
			ItemReader<CursoIfpb> reader,
			@Qualifier("assemblerCursoReduced")
			ItemProcessor<CursoIfpb, CursoIfpbReduced> processor,
			@Qualifier("jsonCursoReducedWriter")
			ItemWriter<CursoIfpbReduced> writer
			) {
		return stepBuilderFactory
			.get("cursosIfpbStepChunk")
			.<CursoIfpb, CursoIfpbReduced>chunk(1)
			.reader(reader)
			.processor(processor)
			.writer(writer)
			.build();
	}
}
