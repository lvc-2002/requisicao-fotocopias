package br.com.lam.model;

import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo", discriminatorType=DiscriminatorType.STRING)
public class Usuario {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String siape;
	
	private String nome;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@Enumerated(EnumType.ORDINAL)
	private Sexo sexo;
	
	private boolean ativo;
	
	@ManyToOne
	private Funcao funcao;
	
	@ManyToOne
	private Setor setor;
	
	@OneToOne
	private Contato contato;
	
	public Usuario() {
		super();
	}

	public Usuario(long id, String siape, String nome, Date dataNascimento,
			Sexo sexo, boolean ativo, Funcao funcao, Setor setor,
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

	public Usuario(String siape, String nome, Date dataNascimento, Sexo sexo,
			boolean ativo, Funcao funcao, Setor setor, Contato contato) {
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

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
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
