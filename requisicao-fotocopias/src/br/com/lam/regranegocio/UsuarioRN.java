package br.com.lam.regranegocio;

import javax.persistence.EntityManager;

import br.com.lam.dao.UsuarioDAO;
import br.com.lam.model.Usuario;

public class UsuarioRN {
	
	private UsuarioDAO dao;
	
	public UsuarioRN(EntityManager em) {
		dao = new UsuarioDAO(em);
	}
	
	public Usuario pesquisa(String siape) {
		Usuario u = dao.pesquisa(siape);
		return u.isAtivo() ? u : null;
	}
	
}
