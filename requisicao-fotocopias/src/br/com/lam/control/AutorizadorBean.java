package br.com.lam.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.lam.dao.UsuarioDAO;
import br.com.lam.model.Requisicao;
import br.com.lam.model.Sexo;
import br.com.lam.model.Status;
import br.com.lam.model.Usuario;

@ManagedBean
@SessionScoped
public class AutorizadorBean {
	
	// Caminho para os conteúdos da tela do Autorizador
	private static final String REQUISICOES_PENDENTES = "/WEB-INF/partials/requisicoesPendentes.xhtml";
	private static final String TODAS_REQUISICOES = "/WEB-INF/partials/todasRequisicoes.xhtml";
	private static final String MINHAS_REQUISICOES = "/WEB-INF/partials/minhasRequisicoes.xhtml";
	private static final String FAZER_REQUISICAO = "/WEB-INF/partials/cadastroRequisicao.xhtml";
	private static final String USUARIOS = "/WEB-INF/partials/usuarios.xhtml";
	private static final String MEUS_DADOS = "/WEB-INF/partials/dadosCadastrais.xhtml";
	
	private String conteudo;
	
	private List<Requisicao> requisicoes;
	private Requisicao requisicao;
	
	private List<Usuario> usuarios;
	
	public AutorizadorBean() {
		requisicoes = new ArrayList<Requisicao>();
		requisicoes.add(new Requisicao(1, "2015/000001", new Date(), 1, null, Status.AGUARDANDO_AUTORIZACAO, new Usuario("12345", "Lázaro Vasconcelos Coutinho", new Date(), Sexo.M, true, null, null, null)));
		requisicoes.add(new Requisicao(2, "2015/000002", new Date(), 2, null, Status.AUTORIZADA, new Usuario("12346", "Artur Vasconcelos Coutinho", new Date(), Sexo.M, true, null, null, null)));
		setConteudo(REQUISICOES_PENDENTES);
	}
	
	public String getConteudo() {
		return conteudo;
	}
	
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
	public List<Requisicao> getRequisicoes() {
		return requisicoes;
	}
	
	public void setRequisicoes(List<Requisicao> requisicoes) {
		this.requisicoes = requisicoes;
	}
	
	public Requisicao getRequisicao() {
		return requisicao;
	}
	
	public void setRequisicao(Requisicao requisicao) {
		this.requisicao = requisicao;
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
	// Métodos que alteram o conteúdo da tela do Autorizador
	public void mostraRequisicoesPendentes() {
		setConteudo(REQUISICOES_PENDENTES);
	}
	
	public void mostraTodasRequisicoes() {
		setConteudo(TODAS_REQUISICOES);
	}
	
	public void mostraRequisicao(long id) {
		requisicao = requisicoes.get((int) id);
	}
	
	public void mostraMinhasRequisicoes() {
		setConteudo(MINHAS_REQUISICOES);
	}
	
	public void mostraFazerRequisicao() {
		setConteudo(FAZER_REQUISICAO);
	}
	
	public void mostraUsuarios() {
		UsuarioDAO dao = new UsuarioDAO(getEntityManager());
		usuarios = dao.lista();
		setConteudo(USUARIOS);
	}
	
	public void mostraMeusDados() {
		setConteudo(MEUS_DADOS);
	}
	
	
	// Métodos auxiliares
	/*public boolean isRequisicoesPendentes() {
		return tipoRequisicao == REQUISICOES_PENDENTES;
	}
	
	public boolean isTodasRequisicoes() {
		return tipoRequisicao == TODAS_REQUISICOES;
	}
	
	public boolean isMinhasRequisicoes() {
		return tipoRequisicao == MINHAS_REQUISICOES;
	}*/
	
	
	// Método gerador de EntityManager
	private EntityManager getEntityManager(){
		return (EntityManager) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("em");
	}

}
