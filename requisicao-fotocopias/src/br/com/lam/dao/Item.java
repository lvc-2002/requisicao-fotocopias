package br.com.lam.dao;

import java.util.List;

import javax.persistence.EntityManager;

public class Item extends DAO implements GenericDAO<Item> {

	public Item(EntityManager em) {
		super(em);
	}

	@Override
	public void salva(Item e) {
		em.persist(e);
	}

	@Override
	public Item pesquisa(long id) {
		return em.find(Item.class, id);
	}

	@Override
	public void exclui(Item e) {
		em.remove(e);
	}

	@Override
	public void atualiza(Item e) {
		em.merge(e);
	}

	@Override
	public List<Item> lista() {
		return em.createQuery("select i from Item i", Item.class).getResultList();
	}

}
