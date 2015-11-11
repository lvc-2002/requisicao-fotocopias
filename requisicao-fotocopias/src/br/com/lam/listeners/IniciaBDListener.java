package br.com.lam.listeners;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class IniciaBDListener implements ServletContextListener {

    public IniciaBDListener() {
    	
    }
    
    public void contextInitialized(ServletContextEvent arg0)  {
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("requisicao-fotocopias");
    	EntityManager em = emf.createEntityManager();
    	arg0.getServletContext().setAttribute("em", em);
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
    	EntityManager em = (EntityManager) arg0.getServletContext().getAttribute("em");
    	EntityManagerFactory emf = em.getEntityManagerFactory();
    	em.close();
    	emf.close();
    }

}
