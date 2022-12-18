package br.edu.ifpb.projetoum.springbatch.curso.lerdataparaarquivo;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import br.edu.ifpb.projetoum.springbatch.model.CursoIfpb;

@Configuration
public class CursosIfpbReaderArquivoConfiguration {
	
	@Bean
	public ItemReader<CursoIfpb> repositoryItemReader() {
		return new RepositoryItemReader<CursoIfpb>();
		
	}

}
