package br.com.lam.listeners;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.lam.util.JPAUtil;

public class IniciaBDListener implements ServletContextListener {

    public IniciaBDListener() {
    	
    }
    
    public void contextInitialized(ServletContextEvent arg0)  {
    	arg0.getServletContext().setAttribute("em", JPAUtil.getEntityManager());
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
    	EntityManager em = (EntityManager) arg0.getServletContext().getAttribute("em");
    	EntityManagerFactory emf = em.getEntityManagerFactory();
    	em.close();
    	emf.close();
    }

}
