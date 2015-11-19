package br.com.lam.regranegocio;

import javax.persistence.EntityManager;

import br.com.lam.dao.UsuarioDAO;
import br.com.lam.model.Usuario;

public class UsuarioRN {
	
	private UsuarioDAO usuarioDao;
	
	public UsuarioRN(EntityManager em) {
		usuarioDao = new UsuarioDAO(em);
	}
	
	public Usuario pesquisa(String siape) {
		Usuario u = usuarioDao.pesquisa(siape);
		return u.isAtivo() ? u : null;
	}
	
}
