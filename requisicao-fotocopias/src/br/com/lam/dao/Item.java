package br.com.lam.dao;

import java.util.List;

import javax.persistence.EntityManager;

public class Item extends DAO implements GenericDAO<Item> {

	public Item(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void salva(Item e) {
		// TODO Auto-generated method stub
		em.persist(e);
	}

	@Override
	public Item pesquisa(long id) {
		// TODO Auto-generated method stub
		return em.find(Item.class, id);
	}

	@Override
	public void exclui(Item e) {
		// TODO Auto-generated method stub
		em.remove(e);
	}

	@Override
	public void atualiza(Item e) {
		// TODO Auto-generated method stub
		em.merge(e);
	}

	@Override
	public List<Item> lista() {
		// TODO Auto-generated method stub
		return em.createQuery("select i from Item i", Item.class).getResultList();
	}

}
