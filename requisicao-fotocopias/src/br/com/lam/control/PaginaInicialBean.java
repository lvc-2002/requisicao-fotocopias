package br.com.lam.control;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.lam.model.Autorizador;
import br.com.lam.model.Executante;
import br.com.lam.model.Solicitante;
import br.com.lam.model.Usuario;
import br.com.lam.regranegocio.UsuarioRN;
import br.com.lam.util.MessagesUtil;

@ManagedBean
public class PaginaInicialBean {
	
	private String siape;
	private String senha;
	
	public String getSiape() {
		return siape;
	}
	
	public void setSiape(String siape) {
		this.siape = siape;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String cadastrar() {
		System.out.println("Usuário");
		return "cadastraUsuario?faces-redirect=true";
	}
	
	public String entrar() {
		UsuarioRN usuarioRn = new UsuarioRN(getEntityManager());
		try {
			Usuario u = usuarioRn.pesquisa(siape);
			if(u != null) {
				if(u.getSenha().equals(senha)) {
					HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
					sessao.setAttribute("usuario", u);
					if(u instanceof Autorizador){
						return "autorizador?faces-redirect=true";
					}else if(u instanceof Solicitante){
						return "solicitante?faces-redirect=true";
					}else if(u instanceof Executante){
						return "atendente?faces-redirect=true";
					}else{
						return null;
					}
				} else {
					MessagesUtil.createMessageError(null, "Acesso negado!", "Usuário e/ou Senha Inválido(s).");
					return null;
				}
			} else {
				MessagesUtil.createMessageError(null, "Acesso negado!", "Entre em contato com algum autorizador.");
				return null;
			}
		} catch (Exception e) {
			MessagesUtil.createMessageError(null, "Usuário não encontrado!", null);
			return null;
		}
	}
	
	public String recuperarSenha() {
		return "recuperaSenha?faces-redirect=true";
	}
	
	private EntityManager getEntityManager(){
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute("em");
	}

}
