package br.com.lam.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("requisicao-fotocopias");
	private static EntityManager em = emf.createEntityManager();
	
	public static EntityManager getEntityManager() {
		return em;
	}

}
