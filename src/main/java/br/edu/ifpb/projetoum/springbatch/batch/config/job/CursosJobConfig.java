package br.edu.ifpb.projetoum.springbatch.batch.config.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CursosJobConfig {
	
	private final JobBuilderFactory jobBuilderFactory;
	
	public CursosJobConfig(
			JobBuilderFactory jobBuilderFactory
		) {
		super();
		this.jobBuilderFactory = jobBuilderFactory;
	}

	@Bean("cursosIfpbJob")
	public Job cursosIfpbJob(@Qualifier("cursosIfpbStepChunk") Step step) {
		return jobBuilderFactory
			.get("cursosIfpbJob")
			.start(step)
			.build();
	}
}
