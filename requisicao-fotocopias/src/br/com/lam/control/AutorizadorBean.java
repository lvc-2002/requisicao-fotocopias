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
import br.com.lam.model.Autorizador;
import br.com.lam.model.Executante;
import br.com.lam.model.Item;
import br.com.lam.model.Requisicao;
import br.com.lam.model.Status;
import br.com.lam.model.Usuario;
import br.com.lam.util.JPAUtil;
import br.com.lam.util.MessagesUtil;

@ManagedBean
@SessionScoped
public class AutorizadorBean {
	
	// Caminho para os conte�dos da tela do Autorizador
	private static final String REQUISICOES_PENDENTES = "/WEB-INF/partials/requisicoesPendentes.xhtml";
	private static final String TODAS_REQUISICOES = "/WEB-INF/partials/todasRequisicoes.xhtml";
	private static final String MINHAS_REQUISICOES = "/WEB-INF/partials/minhasRequisicoes.xhtml";
	private static final String FAZER_REQUISICAO = "/WEB-INF/partials/cadastroRequisicao.xhtml";
	private static final String USUARIOS = "/WEB-INF/partials/usuarios.xhtml";
	private static final String NOVO_USUARIO = "/WEB-INF/partials/novoUsuario.xhtml";
	private static final String MEUS_DADOS = "/WEB-INF/partials/dadosCadastrais.xhtml";
	
	private Usuario usuarioLogado;
	private String novaSenha;
	
	private String conteudo;
	
	private List<Requisicao> requisicoes;
	private Requisicao requisicao;
	private Item item;
	
	private List<Usuario> usuarios;
	private Usuario usuario;
	private String confirmaSenha;
	
	public AutorizadorBean() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		usuarioLogado = (Usuario) sessao.getAttribute("usuario");
		setConteudo(REQUISICOES_PENDENTES);
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
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
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
	
	// M�todos que alteram o conte�do da tela do Autorizador
	public void mostraRequisicoesPendentes() {
		setConteudo(REQUISICOES_PENDENTES);
	}
	
	public void mostraTodasRequisicoes() {
		RequisicaoDAO dao = new RequisicaoDAO(getEntityManager());
		requisicoes = dao.lista();
		setConteudo(TODAS_REQUISICOES);
	}
	
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
	
	public void mostraUsuarios() {
		UsuarioDAO dao = new UsuarioDAO(getEntityManager());
		usuarios = dao.lista(usuarioLogado);
		setConteudo(USUARIOS);
	}
	
	public void mostraMeusDados() {
		setConteudo(MEUS_DADOS);
	}
	
	
	// A��es da tela
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
		MessagesUtil.createMessageInfo(null, "Requisi��o cadastrada com sucesso!", null);
	}
	
	public void preparaNovoAtendente() {
		usuario = new Executante();
		setConteudo(NOVO_USUARIO);
	}
	
	public void preparaNovoAutorizador() {
		usuario = new Autorizador();
		setConteudo(NOVO_USUARIO);
	}
	
	public void autorizarUsuario(long id) {
		UsuarioDAO dao = new UsuarioDAO(getEntityManager());
		Usuario u = dao.pesquisa(id);
		u.setAtivo(true);
		JPAUtil.getEntityManager().getTransaction().begin();
		dao.atualiza(u);
		JPAUtil.getEntityManager().getTransaction().commit();
		MessagesUtil.createMessageInfo(null, "Usu�rio autorizado!", null);
	}
	
	public void rejeitarUsuario(long id) {
		UsuarioDAO dao = new UsuarioDAO(getEntityManager());
		Usuario u = dao.pesquisa(id);
		JPAUtil.getEntityManager().getTransaction().begin();
		dao.exclui(u);
		usuarios.remove(u);
		JPAUtil.getEntityManager().getTransaction().commit();
		MessagesUtil.createMessageInfo(null, "Usu�rio rejeitado!", null);
	}
	
	public void desativarUsuario(long id) {
		UsuarioDAO dao = new UsuarioDAO(getEntityManager());
		Usuario u = dao.pesquisa(id);
		u.setAtivo(false);
		JPAUtil.getEntityManager().getTransaction().begin();
		dao.atualiza(u);
		JPAUtil.getEntityManager().getTransaction().commit();
		MessagesUtil.createMessageInfo(null, "Usu�rio desativado!", null);
	}
	
	public void salvaUsuario() {
		JPAUtil.getEntityManager().getTransaction().begin();
		UsuarioDAO dao = new UsuarioDAO(getEntityManager());
		usuario.setAtivo(true);
		dao.salva(usuario);
		JPAUtil.getEntityManager().getTransaction().commit();
		MessagesUtil.createMessageInfo(null, "Usu�rio cadastrado com sucesso!", null);
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
	
	
	// M�todos auxiliares
	public Date createDataAtual() {
		return new Date();
	}
	
	// M�todo gerador de EntityManager
	private EntityManager getEntityManager(){
		return (EntityManager) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("em");
	}

}
