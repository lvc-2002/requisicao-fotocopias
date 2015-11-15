package br.com.lam.control;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.lam.dao.UsuarioDAO;
import br.com.lam.model.Solicitante;
import br.com.lam.model.Usuario;

@ManagedBean
public class UsuarioBean {
	
	private Usuario usuario;
	private int tipo;
	private String confirmaSenha;
	
	public UsuarioBean() {
		usuario = new Solicitante();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String getConfirmaSenha() {
		return confirmaSenha;
	}
	
	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}
	
	public int getTipo() {
		return tipo;
	}
	
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public String salvaUsuario() {
		UsuarioDAO dao = new UsuarioDAO(getEntityManager());
		EntityTransaction et = getEntityManager().getTransaction();
		et.begin();
		dao.salva(usuario);
		et.commit();
		return "sucesso?faces-redirect=true";
	}
	
	// Método gerador de EntityManager
	private EntityManager getEntityManager(){
		return (EntityManager) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("em");
		//return JPAUtil.getEntityManager();
	}

}
