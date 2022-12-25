package br.edu.ifpb.projetoum.springbatch.curso.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class CursoJobXlsxToDbConfig {
	
	private final JobBuilderFactory jobBuilderFactory;
	
	public CursoJobXlsxToDbConfig(
			JobBuilderFactory jobBuilderFactory
		) {
		super();
		this.jobBuilderFactory = jobBuilderFactory;
	}

	@Bean("cursosIfpbJobXlsx")
	public Job cursosIfpbJobXlsx(@Qualifier("cursosIfpbParaArquivo") Step csvToDatabase,
			@Qualifier("cursosStepLeitura") Step stepDataReader) {
		return jobBuilderFactory
			.get("cursosIfpbJobXlsx")
			.start(csvToDatabase)
			.next(stepDataReader)
			.incrementer(new RunIdIncrementer())
			.build();
	}
}
