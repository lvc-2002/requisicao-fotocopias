package br.com.lam.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.lam.model.Autorizador;
import br.com.lam.model.Usuario;

public class ControleDeAcessoAutorizadorFilter implements Filter {

    public ControleDeAcessoAutorizadorFilter() {

    }

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession sessao = ((HttpServletRequest) request).getSession();
		Usuario u = (Usuario) sessao.getAttribute("usuario");
		if(u != null && u.isAtivo() && u instanceof Autorizador){
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse) response).sendRedirect("index.xhtml");
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
