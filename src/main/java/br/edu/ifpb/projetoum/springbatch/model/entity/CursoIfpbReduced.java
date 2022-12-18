package br.edu.ifpb.projetoum.springbatch.model.entity;

import java.text.MessageFormat;

public class CursoIfpbReduced {
	private Long identificador;
	private String descricao;
	private String coordenador;
	private ModalidadeCurso modalidade;
	
	public CursoIfpbReduced() {}
	
	public Long getIdentificador() {
		return identificador;
	}
	public void setIdentificador(Long identificador) {
		this.identificador = identificador;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao, Long cargaHoraria) {
		this.descricao = MessageFormat.format("{0} - {1}h", descricao, cargaHoraria);
	}
	public String getCoordenador() {
		return coordenador;
	}
	public void setCoordenador(String coordenador) {
		this.coordenador = coordenador;
	}
	public ModalidadeCurso getModalidade() {
		return modalidade;
	}
	public void setModalidade(ModalidadeCurso modalidade) {
		this.modalidade = modalidade;
	}
	
	
}
