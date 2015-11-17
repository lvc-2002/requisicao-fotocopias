package br.com.lam.control;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import br.com.lam.dao.RequisicaoDAO;
import br.com.lam.dao.UsuarioDAO;
import br.com.lam.model.Item;
import br.com.lam.model.Requisicao;
import br.com.lam.model.Status;
import br.com.lam.model.Usuario;
import br.com.lam.util.JPAUtil;
import br.com.lam.util.MessagesUtil;

@ManagedBean
@SessionScoped
public class SolicitanteBean {
	
	// Caminho para os conteúdos da tela do Autorizador
	private static final String MINHAS_REQUISICOES = "/WEB-INF/partials/minhasRequisicoes.xhtml";
	private static final String FAZER_REQUISICAO = "/WEB-INF/partials/cadastroRequisicao.xhtml";
	private static final String MEUS_DADOS = "/WEB-INF/partials/dadosCadastrais.xhtml";
	
	private Usuario usuarioLogado;
	private String novaSenha;
	
	private String conteudo;
	
	private List<Requisicao> requisicoes;
	private Requisicao requisicao;
	private Item item;
	
	private String confirmaSenha;
	
	public SolicitanteBean() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		usuarioLogado = (Usuario) sessao.getAttribute("usuario");
		setConteudo(MINHAS_REQUISICOES);
	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	public String getNovaSenha() {
		return novaSenha;
	}
	
	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
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
	
	public Item getItem() {
		return item;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	public String getConfirmaSenha() {
		return confirmaSenha;
	}
	
	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}
	
	// Métodos que alteram o conteúdo da tela do Autorizador
	public void mostraRequisicao(long id) {
		requisicao = requisicoes.get((int) id);
	}
	
	public void mostraMinhasRequisicoes() {
		setConteudo(MINHAS_REQUISICOES);
	}
	
	public void mostraFazerRequisicao() {
		requisicao = new Requisicao();
		item = new Item();
		setConteudo(FAZER_REQUISICAO);
	}
	
	public void mostraMeusDados() {
		setConteudo(MEUS_DADOS);
	}
	
	
	// Ações da tela
	public void adicionaItemRequisicao() {
		item.setRequisicao(requisicao);
		item.setNumero(requisicao.getItens().size() + 1);
		requisicao.getItens().add(item);
		int j = requisicao.getTotal();
		j = j + item.getQuantidade();
		requisicao.setTotal(j);
		item = new Item();
	}
	
	public void salvaRequisicao() {
		RequisicaoDAO dao = new RequisicaoDAO(getEntityManager());
		JPAUtil.getEntityManager().getTransaction().begin();
		requisicao.setData(createDataAtual());
		requisicao.setStatus(Status.AGUARDANDO_AUTORIZACAO);
		requisicao.setUsuario(usuarioLogado);
		dao.salva(requisicao);
		JPAUtil.getEntityManager().getTransaction().commit();
		MessagesUtil.createMessageInfo(null, "Requisição cadastrada com sucesso!", null);
	}
	
	public void alteraDadosCadastrais() {
		JPAUtil.getEntityManager().getTransaction().begin();
		UsuarioDAO dao = new UsuarioDAO(getEntityManager());
		dao.atualiza(usuarioLogado);
		JPAUtil.getEntityManager().getTransaction().commit();MessagesUtil.createMessageInfo(null, "Dados alterados com sucesso!", null);
	}
	
	public void alteraSenha() {
		/*JPAUtil.getEntityManager().getTransaction().begin();
		UsuarioDAO dao = new UsuarioDAO(getEntityManager());
		usuarioLogado.setSenha(novaSenha);
		dao.atualiza(usuarioLogado);
		JPAUtil.getEntityManager().getTransaction().commit();
		MessagesUtil.createMessageInfo(null, "Senha alterada com sucesso!", null);*/
		System.out.println("oi");
	}
	
	public String sair() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		sessao.invalidate();
		return "index?faces-redirect=true";
	}
	
	
	// Métodos auxiliares
	public Date createDataAtual() {
		return new Date();
	}
	
	// Método gerador de EntityManager
	private EntityManager getEntityManager(){
		return (EntityManager) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("em");
	}

}
