package br.edu.ifpb.projetoum.springbatch.curso.lerdataparaarquivo;

import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.edu.ifpb.projetoum.springbatch.model.CursoIfpb;
import br.edu.ifpb.projetoum.springbatch.model.CursoIfpbReduced;

@Configuration
public class CursosIfpbToReducedProcessorConfiguration {

	@Bean("assemblerCursoReduced")
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
