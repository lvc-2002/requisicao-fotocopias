package br.com.lam.model;

public enum Status {
	
	EM_PROCESSO("Em Processo"),
	AGUARDANDO_AUTORIZACAO("Aguardando Autorização"),
	NAO_AUTORIZADA("Não Autorizada"),
	AUTORIZADA("Autorizada"),
	NAO_ATENDIDA("Não Atendida"),
	ATENDIDA("Atendida");
	
	public String valor;
	
	Status(String valor) {
		this.valor = valor;
	}
	
	public String getValor() {
		return valor;
	}

}
