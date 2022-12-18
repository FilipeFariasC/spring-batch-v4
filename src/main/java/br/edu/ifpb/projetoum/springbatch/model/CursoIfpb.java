package br.edu.ifpb.projetoum.springbatch.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="curso_ifpb")
public class CursoIfpb {
	
	@Id
	private Long codigo;
	private String descricao;
	private String diretoria;
	private String naturezaParticipacao;
	private String eixo;
	@Enumerated(EnumType.STRING)
	private ModalidadeCurso modalidade;
	private String resolucaoCriacao;
	private String coordenador;
	private Long cargaHoraria;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDiretoria() {
		return diretoria;
	}
	public void setDiretoria(String diretoria) {
		this.diretoria = diretoria;
	}
	public String getNaturezaParticipacao() {
		return naturezaParticipacao;
	}
	public void setNaturezaParticipacao(String naturezaParticipacao) {
		this.naturezaParticipacao = naturezaParticipacao;
	}
	public String getEixo() {
		return eixo;
	}
	public void setEixo(String eixo) {
		this.eixo = eixo;
	}
	public ModalidadeCurso getModalidade() {
		return modalidade;
	}
	public void setModalidade(ModalidadeCurso modalidade) {
		this.modalidade = modalidade;
	}
	public String getResolucaoCriacao() {
		return resolucaoCriacao;
	}
	public void setResolucaoCriacao(String resolucaoCriacao) {
		this.resolucaoCriacao = resolucaoCriacao;
	}
	public String getCoordenador() {
		return coordenador;
	}
	public void setCoordenador(String coordenador) {
		this.coordenador = coordenador;
	}
	public Long getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(Long cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	
}
