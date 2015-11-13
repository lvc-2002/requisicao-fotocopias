package br.com.lam.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Contato {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String telefone;
	
	private String celular;
	
	private String email;
	
	@OneToOne
	private Usuario usuario;
	
	public Contato() {
		super();
	}

	public Contato(long id, String telefone, String celular, String email,
			Usuario usuario) {
		super();
		this.id = id;
		this.telefone = telefone;
		this.celular = celular;
		this.email = email;
		this.usuario = usuario;
	}

	public Contato(String telefone, String celular, String email,
			Usuario usuario) {
		super();
		this.telefone = telefone;
		this.celular = celular;
		this.email = email;
		this.usuario = usuario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		Contato other = (Contato) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contato [id=" + id + ", telefone=" + telefone + ", celular="
				+ celular + ", email=" + email + ", usuario=" + usuario + "]";
	}

}
