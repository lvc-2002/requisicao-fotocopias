package br.com.lam.model;

public class Contato {
	
	private long id;
	private String telefone;
	private String celular;
	private String email;
	
	public Contato() {
		super();
	}

	public Contato(long id, String telefone, String celular, String email) {
		super();
		this.id = id;
		this.telefone = telefone;
		this.celular = celular;
		this.email = email;
	}

	public Contato(String telefone, String celular, String email) {
		super();
		this.telefone = telefone;
		this.celular = celular;
		this.email = email;
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
				+ celular + ", email=" + email + "]";
	}

}
