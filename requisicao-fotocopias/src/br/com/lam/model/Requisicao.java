package br.com.lam.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Requisicao {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String numero;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@Transient
	private int total;
	
	@OneToMany(mappedBy="requisicao", cascade={CascadeType.ALL})
	private List<Item> itens;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Autorizador autorizador;
	
	public Requisicao() {
		super();
		itens = new ArrayList<Item>();
	}

	public Requisicao(long id, String numero, Date data, int total, List<Item> itens, Status status, Usuario usuario ) {
		super();
		this.id = id;
		this.numero = numero;
		this.data = data;
		this.total = total;
		this.itens = itens;
		this.status = status;
		this.usuario = usuario;
	}

	public Requisicao(String numero, Date data, int total) {
		super();
		this.numero = numero;
		this.data = data;
		this.total = total;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Item> getItens() {
		return itens;
	}
	
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Autorizador getAutorizador() {
		return autorizador;
	}
	
	public void setAutorizador(Autorizador autorizador) {
		this.autorizador = autorizador;
	}
	
	public int totalQuantidadeItens() {
		int j = 0;
		for(Item i : itens){
			j = j + i.getQuantidade();
		}
		return j;
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
		Requisicao other = (Requisicao) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Requisicao [id=" + id + ", numero=" + numero + ", data=" + data
				+ ", total=" + total + ", itens=" + itens + ", status="
				+ status + ", usuario=" + usuario + "]";
	}

}
