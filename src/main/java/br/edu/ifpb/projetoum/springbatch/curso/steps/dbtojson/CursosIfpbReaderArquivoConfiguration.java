package br.edu.ifpb.projetoum.springbatch.curso.steps.dbtojson;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort.Direction;

import br.edu.ifpb.projetoum.springbatch.model.entity.CursoIfpb;
import br.edu.ifpb.projetoum.springbatch.model.repository.CursosIfpbRepository;



@Configuration
public class CursosIfpbReaderArquivoConfiguration {
	
	@Autowired
	private CursosIfpbRepository repository;
	
	@Bean("databaseToCursoIfpb")
	public ItemReader<CursoIfpb> repositoryItemReader() {
		
		RepositoryItemReader<CursoIfpb> repositorio = new RepositoryItemReader<CursoIfpb>();
		repositorio.setRepository(repository);
		repositorio.setMethodName("findAll");
		
		Map<String, Direction> map = new HashMap<>();
		map.put("codigo", Direction.ASC);
		repositorio.setSort(map);
		
		return repositorio;
		
	}
	

}
