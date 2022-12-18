package br.edu.ifpb.projetoum.springbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
public class SpringBatchV4UpcensusTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchV4UpcensusTestApplication.class, args);
	}

}
