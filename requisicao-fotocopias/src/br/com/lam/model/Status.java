package br.com.lam.model;

public enum Status {
	
	EM_PROCESSO("Em Processo"),
	AGUARDANDO_AUTORIZACAO("Aguardando Autoriza��o"),
	NAO_AUTORIZADA("N�o Autorizada"),
	AUTORIZADA("Autorizada"),
	NAO_ATENDIDA("N�o Atendida"),
	ATENDIDA("Atendida");
	
	public String valor;
	
	Status(String valor) {
		this.valor = valor;
	}
	
	public String getValor() {
		return valor;
	}

}
