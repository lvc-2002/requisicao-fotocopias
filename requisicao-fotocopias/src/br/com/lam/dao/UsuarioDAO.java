package br.com.lam.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.lam.model.Usuario;

public class UsuarioDAO extends DAO implements GenericDAO<Usuario>{

	public UsuarioDAO(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void salva(Usuario e) {
		em.persist(e);
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario pesquisa(long id) {
		// TODO Auto-generated method stub
		return em.find(Usuario.class, id);
	}

	@Override
	public void exclui(Usuario e) {
		em.remove(e);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualiza(Usuario e) {
		em.merge(e);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Usuario> lista() {
		// TODO Auto-generated method stub
		return em.createQuery("select u from Usuario u", Usuario.class).getResultList();
	}
	
	public Usuario pesquisa(String siape, String senha) {
		List<Usuario> usuarios = lista();
		for(Usuario u : usuarios) {
			if(u.getSiape().equals(siape) && u.getSenha().equals(senha)) {
				return u;
			}
		}
		return null;
	}

}
