package br.edu.ifpb.projetoum.springbatch.model;

public class CursoIfpbReduced {
	private Long identificador;
	private String descricaoECargaHoraria;
	private String coordenador;
	private ModalidadeCurso modalidade;
	
	public CursoIfpbReduced() {}
	
	public Long getIdentificador() {
		return identificador;
	}
	public void setIdentificador(Long identificador) {
		this.identificador = identificador;
	}
	public String getDescricaoECargaHoraria() {
		return descricaoECargaHoraria;
	}
	public void setDescricaoECargaHoraria(String descricaoECargaHoraria) {
		this.descricaoECargaHoraria = descricaoECargaHoraria;
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
