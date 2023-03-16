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
public class CursosJobConfig {
	
	
	private final JobBuilderFactory jobBuilderFactory;
	
	public CursosJobConfig(
			JobBuilderFactory jobBuilderFactory
		) {
		super();
		this.jobBuilderFactory = jobBuilderFactory;
	}

	@Bean("cursosIfpbJob")
	public Job cursosIfpbJob(@Qualifier("cursosIfpbParaArquivo") Step csvToDatabase,
			@Qualifier("cursosStepLeitura") Step stepDataReader) {
		return jobBuilderFactory
			.get("cursosIfpbJob")
			.start(csvToDatabase)
			.next(stepDataReader)
			.incrementer(new RunIdIncrementer())
			.build();
	}
}
