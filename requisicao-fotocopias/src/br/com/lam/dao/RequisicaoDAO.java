package br.com.lam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.lam.model.Requisicao;
import br.com.lam.model.Usuario;

public class RequisicaoDAO extends DAO implements GenericDAO<Requisicao> {

	public RequisicaoDAO(EntityManager em) {
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
	
	@SuppressWarnings("unchecked")
	public List<Requisicao> lista (Usuario u) {
		Query q = em.createQuery("select u from Requisicao u where u.id == :id");
		q.setParameter("id", u.getId());
		return q.getResultList();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Requisicao> listaRequiscoes(String status) {
		Query q = em.createQuery("select r from Requisicao r wehre r.status == :status");
		q.setParameter("status", status);
		return q.getResultList();
		
	}
}
