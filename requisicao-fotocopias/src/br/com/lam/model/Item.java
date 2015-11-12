package br.com.lam.model;

public class Item {
	
	private long id;
	private int numero;
	private String discriminacao;
	private int quantidade;
	private Requisicao requisicao;
	
	public Item() {
		super();
	}

	public Item(long id, int numero, String discriminacao, int quantidade, Requisicao requisicao) {
		super();
		this.id = id;
		this.numero = numero;
		this.discriminacao = discriminacao;
		this.quantidade = quantidade;
		this.requisicao = requisicao;
	}

	public Item(int numero, String discriminacao, int quantidade) {
		super();
		this.numero = numero;
		this.discriminacao = discriminacao;
		this.quantidade = quantidade;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getDiscriminacao() {
		return discriminacao;
	}

	public void setDiscriminacao(String discriminacao) {
		this.discriminacao = discriminacao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Requisicao getRequisicao() {
		return requisicao;
	}
	
	public void setRequisicao(Requisicao requisicao) {
		this.requisicao = requisicao;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", numero=" + numero + ", discriminacao="
				+ discriminacao + ", quantidade=" + quantidade
				+ ", requisicao=" + requisicao + "]";
	}
	
}
