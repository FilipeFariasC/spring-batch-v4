package br.edu.ifpb.projetoum.springbatch.oli.process;

import org.springframework.batch.item.ItemProcessor;
import br.edu.ifpb.projetoum.springbatch.oli.model.Curso;


public class CursoProcessor implements ItemProcessor<Curso, Curso>{


	public Curso process(Curso item) throws Exception {
		final String nome = item.getNome().toUpperCase();
		final String periodo = item.getPeriodo().toUpperCase();
		final Curso data = new Curso(nome, periodo);
		return data;
	}
}
