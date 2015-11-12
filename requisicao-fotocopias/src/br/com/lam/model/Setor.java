package br.com.lam.model;

public class Setor {
	
	private long id;
	private String descrição;
	
	public Setor() {
		super();
	}
	
	public Setor(long id, String descrição) {
		super();
		this.id = id;
		this.descrição = descrição;
	}
	
	public Setor(String descrição) {
		super();
		this.descrição = descrição;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getDescrição() {
		return descrição;
	}
	
	public void setDescrição(String descrição) {
		this.descrição = descrição;
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
		return "Setor [id=" + id + ", descrição=" + descrição + "]";
	}

}
