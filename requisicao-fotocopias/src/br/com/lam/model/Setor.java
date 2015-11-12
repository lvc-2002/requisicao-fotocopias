package br.com.lam.model;

import java.util.List;

public class Setor {
	
	private long id;
	private String descrição;
	
	private List<Usuario> usuarios;
	
	public Setor() {
		super();
	}
	
	
	public Setor(long id, String descrição, List<Usuario> usuarios) {
		super();
		this.id = id;
		this.descrição = descrição;
		this.usuarios = usuarios;
	}
	

	public Setor(String descrição, List<Usuario> usuarios) {
		super();
		this.descrição = descrição;
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
		return "Setor [id=" + id + ", descrição=" + descrição + ", usuarios="
				+ usuarios + "]";
	}
	
	

}
