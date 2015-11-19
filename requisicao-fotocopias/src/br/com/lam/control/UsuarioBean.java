package br.com.lam.control;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;

import br.com.lam.dao.UsuarioDAO;
import br.com.lam.model.Solicitante;
import br.com.lam.model.Usuario;
import br.com.lam.util.JPAUtil;
import br.com.lam.util.MessagesUtil;

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
		getEntityManager().getTransaction().begin();
		try {
			usuario.setRejeitado(true);
			if(usuario.getSenha().equals(confirmaSenha)){
				dao.salva(usuario);
				getEntityManager().getTransaction().commit();
				return "sucesso?faces-redirect=true";
			}else {
				MessagesUtil.createMessageError(null, "A confirmação da senha está incorreta!", null);
				getEntityManager().getTransaction().rollback();
				return null;
			}
		} catch (Exception e) {
			getEntityManager().getTransaction().rollback();
			MessagesUtil.createMessageError(null, "O Siape informado já existe!", null);
		}
		return null;
	}
	
	// Método gerador de EntityManager
	private EntityManager getEntityManager(){
		/*FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute("em");*/
		return JPAUtil.getEntityManager();
	}

}
