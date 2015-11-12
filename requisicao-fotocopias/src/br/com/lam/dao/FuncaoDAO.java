package br.com.lam.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.lam.model.Funcao;

public class FuncaoDAO extends DAO implements GenericDAO<Funcao>{

	public FuncaoDAO(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void salva(Funcao e) {
		em.persist(e);
		// TODO Auto-generated method stub
		
	}

	@Override
	public Funcao pesquisa(long id) {
		// TODO Auto-generated method stub
		return em.find(Funcao.class, id);
	}

	@Override
	public void exclui(Funcao e) {
		em.remove(e);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualiza(Funcao e) {
		em.merge(e);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Funcao> lista() {
		// TODO Auto-generated method stub
		return em.createQuery("select f from Funcao f", Funcao.class).getResultList();
	}

}
