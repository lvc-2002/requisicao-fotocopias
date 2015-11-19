package br.com.lam.filters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class JPAFilter implements Filter {
	
	private EntityManagerFactory emf;
	
    public JPAFilter() {
    	
    }

    public void init(FilterConfig fConfig) throws ServletException {
		emf = Persistence.createEntityManagerFactory("requisicao-fotocopias");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		EntityManager em = emf.createEntityManager();
		request.setAttribute("em", em);
		em.getTransaction().begin();
		
		chain.doFilter(request, response);
		
		try {
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}
	
	public void destroy() {
		emf.close();
	}

}
