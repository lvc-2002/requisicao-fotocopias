package br.com.lam.model;

public class Setor {
	
	private long id;
	private String descri��o;
	
	public Setor() {
		super();
	}
	
	public Setor(long id, String descri��o) {
		super();
		this.id = id;
		this.descri��o = descri��o;
	}
	
	public Setor(String descri��o) {
		super();
		this.descri��o = descri��o;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getDescri��o() {
		return descri��o;
	}
	
	public void setDescri��o(String descri��o) {
		this.descri��o = descri��o;
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
		return "Setor [id=" + id + ", descri��o=" + descri��o + "]";
	}

}
