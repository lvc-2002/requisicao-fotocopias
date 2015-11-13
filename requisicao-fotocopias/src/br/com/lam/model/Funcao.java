package br.com.lam.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Funcao {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String descricao;
	
	@OneToMany(mappedBy="funcao")
	private List<Usuario> usuarios;
	
	public Funcao() {
		super();
	}

	public Funcao(long id, String descricao, List<Usuario> usuarios) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.usuarios = usuarios;
	}

	public Funcao(String descricao, List<Usuario> usuarios) {
		super();
		this.descricao = descricao;
		this.usuarios = usuarios;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
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
		Funcao other = (Funcao) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Funcao [id=" + id + ", descricao=" + descricao + ", usuarios="
				+ usuarios + "]";
	}
	
}
