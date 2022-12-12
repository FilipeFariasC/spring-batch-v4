package br.edu.ifpb.projetoum.springbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchV4UpcensusTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchV4UpcensusTestApplication.class, args);
	}

}
