package br.com.lam.dao;

import java.util.List;

import javax.persistence.EntityManager;

public class Requisicao extends DAO implements GenericDAO<Requisicao> {

	public Requisicao(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void salva(Requisicao e) {
		// TODO Auto-generated method stub
		em.persist(e);
	}

	@Override
	public Requisicao pesquisa(long id) {
		// TODO Auto-generated method stub
		return em.find(Requisicao.class, id);
	}

	@Override
	public void exclui(Requisicao e) {
		// TODO Auto-generated method stub
		em.remove(e);
	}

	@Override
	public void atualiza(Requisicao e) {
		// TODO Auto-generated method stub
		em.merge(e);
	}

	@Override
	public List<Requisicao> lista() {
		// TODO Auto-generated method stub
		return em.createQuery("select r from Requisicao r", Requisicao.class).getResultList();
	}

}
