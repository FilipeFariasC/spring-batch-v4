package br.edu.ifpb.projetoum.springbatch.model.entity;

import java.util.stream.Stream;

public enum ModalidadeCurso {
	BACHARELADO("Bacharelado"),
	CONCOMITANTE("Concomitante"),
	ESPECIALIZACAO("Especialização"),
	FIC("FIC"),
	INTEGRADO("Integrado"),
	INTEGRADO_EJA("Integrado EJA"),
	LICENCIATURA("Licenciatura"),
	MESTRADO("Mestrado"),
	SUBSEQUENTE("Subsequente"),
	TECNOLOGIA("Tecnologia");
	
	private String modalidade;
	
	ModalidadeCurso(String modalidade) {
		this.modalidade = modalidade;
	}
	public static ModalidadeCurso from(Object value) {
		return from(value.toString());
	}
	
	public static ModalidadeCurso from(String value) {
		return Stream.of(ModalidadeCurso.values())
			.filter(modalidade->modalidade.getModalidade().equals(value))
			.findFirst()
			.orElse(null);
	}

	public String getModalidade() {
		return modalidade;
	}

}
