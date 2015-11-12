package br.com.lam.dao;

import javax.persistence.EntityManager;

public class DAO {
	
	protected EntityManager em;
	
	public DAO(EntityManager em) {
		this.em = em;
	}

}
