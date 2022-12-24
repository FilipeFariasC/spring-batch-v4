package br.edu.ifpb.projetoum.springbatch.resources;

public class ArquivoResponse {
	
	private String caminho;
	
	public ArquivoResponse() { }
	public ArquivoResponse(String caminho) {
		setCaminho(caminho);
	}
	
	public static ArquivoResponse of(String caminho) {
		return new ArquivoResponse(caminho);
	}
	
	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	
}
