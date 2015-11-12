package br.com.lam.model;

import java.util.Date;
import java.util.List;

public class Usuario {
	
	private long id;
	private String siape;
	private String nome;
	private Date dataNascimento;
	private String sexo;
	private int ativo;
	
	private Funcao funcao;
	
	private Setor setor;
	
	private Contato contato;
	
	public Usuario() {
		super();
	}

	

	public Usuario(long id, String siape, String nome, Date dataNascimento,
			String sexo, int ativo, Funcao funcao, Setor setor,
			Contato contato) {
		super();
		this.id = id;
		this.siape = siape;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.ativo = ativo;
		this.funcao = funcao;
		this.setor = setor;
		this.contato = contato;
	}

	public Usuario(String siape, String nome, Date dataNascimento, String sexo,
			int ativo, Funcao funcao, Setor setor, Contato contato) {
		super();
		this.siape = siape;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.ativo = ativo;
		this.funcao = funcao;
		this.setor = setor;
		this.contato = contato;
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSiape() {
		return siape;
	}

	public void setSiape(String siape) {
		this.siape = siape;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}
	
	

	public Funcao getFuncao() {
		return funcao;
	}



	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}



	public Setor getSetor() {
		return setor;
	}



	public void setSetor(Setor setor) {
		this.setor = setor;
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
		Usuario other = (Usuario) obj;
		if (id != other.id)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Usuario [id=" + id + ", siape=" + siape + ", nome=" + nome
				+ ", dataNascimento=" + dataNascimento + ", sexo=" + sexo
				+ ", ativo=" + ativo + ", funcao=" + funcao + ", setor="
				+ setor + ", contato=" + contato +
				"]";
	}
	
	

}
