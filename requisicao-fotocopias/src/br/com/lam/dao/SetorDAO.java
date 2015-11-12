package br.com.lam.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.lam.model.Setor;

public class SetorDAO extends DAO implements GenericDAO<Setor>{

	public SetorDAO(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void salva(Setor e) {
		em.persist(e);
		// TODO Auto-generated method stub
		
	}

	@Override
	public Setor pesquisa(long id) {
		// TODO Auto-generated method stub
		return em.find(Setor.class, id);
	}

	@Override
	public void exclui(Setor e) {
		em.remove(e);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualiza(Setor e) {
		em.merge(e);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Setor> lista() {
		// TODO Auto-generated method stub
		return em.createQuery("select s from Setor s", Setor.class).getResultList();
	}
	
	

}
