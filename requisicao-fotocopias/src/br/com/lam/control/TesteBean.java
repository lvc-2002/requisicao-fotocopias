package br.com.lam.control;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.lam.model.Aluno;

@ManagedBean
public class TesteBean {
	
	private List<Aluno> alunos;
	
	public TesteBean() {
		alunos = new ArrayList<Aluno>();
		alunos.add(new Aluno(1, "Lázaro", 23));
		alunos.add(new Aluno(2, "Artur", 20));
	}
	
	public List<Aluno> getAlunos() {
		return alunos;
	}
	
	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

}
