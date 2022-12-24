package br.edu.ifpb.projetoum.springbatch.curso.steps.csvtodb.writer;

import javax.persistence.EntityManager;

import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.edu.ifpb.projetoum.springbatch.model.entity.CursoIfpb;

@Configuration
public class CursosIfpbToDatabaseWriterConfiguration {
	
	@Bean("cursoToDatabaseWriter")
	public JpaItemWriter<CursoIfpb> cursoIfpbToDatabaseWriter(EntityManager entityManager) {
		JpaItemWriter<CursoIfpb> writer = new JpaItemWriter<>();
		writer.setEntityManagerFactory(entityManager.getEntityManagerFactory());
		return writer;
	}
	
}
