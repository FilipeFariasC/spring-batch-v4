package br.edu.ifpb.projetoum.springbatch.batch.config.processor;

import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.edu.ifpb.projetoum.springbatch.model.CursoIfpb;
import br.edu.ifpb.projetoum.springbatch.model.CursoIfpbReduced;

@Configuration
public class CursosIfpbProcessorConfiguration {

	@Bean("assemblerCursoReduced")
	public FunctionItemProcessor<CursoIfpb, CursoIfpbReduced> parOuImparProcessor() {
		return new FunctionItemProcessor<>(this::modelToReduced);
	}
    
	private CursoIfpbReduced modelToReduced(CursoIfpb curso) {
		CursoIfpbReduced reduced = new CursoIfpbReduced();

		reduced.setIdentificador(curso.getCodigo());
		reduced.setDescricaoECargaHoraria(String.format("%s - %d", curso.getDescricao(), curso.getCargaHoraria()));
		reduced.setCoordenador(curso.getCoordenador());
		reduced.setModalidade(curso.getModalidade());
		
		return reduced;
	}
}
