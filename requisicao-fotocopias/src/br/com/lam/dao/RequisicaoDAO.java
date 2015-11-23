package br.com.lam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.lam.model.Requisicao;
import br.com.lam.model.Status;
import br.com.lam.model.Usuario;

public class RequisicaoDAO extends DAO implements GenericDAO<Requisicao> {

	public RequisicaoDAO(EntityManager em) {
		super(em);
	}

	@Override
	public void salva(Requisicao e) {
		em.persist(e);
	}

	@Override
	public Requisicao pesquisa(long id) {
		return em.find(Requisicao.class, id);
	}

	@Override
	public void exclui(Requisicao e) {
		em.remove(e);
	}

	@Override
	public void atualiza(Requisicao e) {
		em.merge(e);
	}

	@Override
	public List<Requisicao> lista() {
		return em.createQuery("select r from Requisicao r", Requisicao.class).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Requisicao> lista (Usuario u) {
		Query q = em.createQuery("select r from Requisicao r where usuario = :u", Requisicao.class);
		q.setParameter("u", u);
		return q.getResultList();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Requisicao> listaRequiscoes(Status status) {
		Query q = em.createQuery("select r from Requisicao r where status = :status", Requisicao.class);
		q.setParameter("status", status);
		return q.getResultList();
		
	}
}
