package br.com.lam.filters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class EntityManagerFilter implements Filter {

    public EntityManagerFilter() {
    	
    }
    
    public void init(FilterConfig fConfig) throws ServletException {
		
	}

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		EntityManager em = (EntityManager) ((HttpServletRequest) request).getServletContext().getAttribute("em");
		
		try {
			em.getTransaction().begin();
			chain.doFilter(request, response);
			em.getTransaction().commit();
		} catch (Throwable e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			throw new ServletException(e);
		} finally {
			em.close();
		}
		
	}

}
