package br.edu.ifpb.projetoum.springbatch.curso.lerdataparaarquivo;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.edu.ifpb.projetoum.springbatch.model.entity.CursoIfpb;
import br.edu.ifpb.projetoum.springbatch.model.entity.CursoIfpbReduced;

@Configuration
public class CursosLeituraJsonStep {

	private final StepBuilderFactory stepBuilderFactory;
	
	public CursosLeituraJsonStep(
			StepBuilderFactory stepBuilderFactory
		) {
		super();
		this.stepBuilderFactory = stepBuilderFactory;
	}
	
	@Bean("cursosStepLeitura")
	public Step cursosStepLeitura(
		@Qualifier("cursosIfpbParaArquivo")
		ItemReader<CursoIfpb> reader,
		@Qualifier("assemblerCursoReduced")
		ItemProcessor<CursoIfpb, CursoIfpbReduced> processor,
		@Qualifier("jsonCursoReducedWriter")
		ItemWriter<CursoIfpbReduced> writer
		) {
	return stepBuilderFactory
		.get("cursosStepLeitura")
		.<CursoIfpb, CursoIfpbReduced>chunk(1)
		.reader(reader)
		.processor(processor)
		.writer(writer)
		.build();
		
	}
}
