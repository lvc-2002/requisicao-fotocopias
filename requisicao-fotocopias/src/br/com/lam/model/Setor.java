package br.com.lam.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Setor {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String descricao;
	
	@OneToMany(mappedBy="setor")
	private List<Usuario> usuarios;
	
	public Setor() {
		super();
	}
	
	public Setor(long id, String descricao, List<Usuario> usuarios) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.usuarios = usuarios;
	}
	
	public Setor(String descricao, List<Usuario> usuarios) {
		super();
		this.descricao = descricao;
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
		Setor other = (Setor) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Setor [id=" + id + ", descrição=" + descricao + ", usuarios="
				+ usuarios + "]";
	}
	
}
