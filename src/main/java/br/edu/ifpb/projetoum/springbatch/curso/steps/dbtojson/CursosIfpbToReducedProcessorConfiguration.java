package br.edu.ifpb.projetoum.springbatch.curso.steps.dbtojson;

import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.edu.ifpb.projetoum.springbatch.model.entity.CursoIfpb;
import br.edu.ifpb.projetoum.springbatch.model.entity.CursoIfpbReduced;

@Configuration
public class CursosIfpbToReducedProcessorConfiguration {

	@Bean("cursoIfpbToReduced")
	public FunctionItemProcessor<CursoIfpb, CursoIfpbReduced> parOuImparProcessor() {
		return new FunctionItemProcessor<>(this::modelToReduced);
	}
    
	private CursoIfpbReduced modelToReduced(CursoIfpb curso) {
		CursoIfpbReduced reduced = new CursoIfpbReduced();

		reduced.setIdentificador(curso.getCodigo());
		reduced.setDescricao(curso.getDescricao(), curso.getCargaHoraria());
		reduced.setCoordenador(curso.getCoordenador());
		reduced.setModalidade(curso.getModalidade());
		
		return reduced;
	}
}
