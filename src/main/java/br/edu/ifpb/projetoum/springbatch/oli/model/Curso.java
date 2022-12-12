package br.edu.ifpb.projetoum.springbatch.oli.model;

public class Curso {
	
	public Curso() {}
	
	public Curso(String nome, String periodo) {
		this.nome = nome;
		this.periodo = periodo;
	}
	
	private String nome;

	private String periodo;
	
	
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public static String[] fields() {
		return new String[] {"nome", "periodo"};
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
