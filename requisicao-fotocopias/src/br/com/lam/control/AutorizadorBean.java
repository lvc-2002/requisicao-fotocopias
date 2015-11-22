package br.com.lam.control;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.model.StreamedContent;

import com.sun.xml.internal.ws.util.UtilException;

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
import br.com.lam.util.RelatorioUtil;

@ManagedBean
@SessionScoped
public class AutorizadorBean {
	
	// Caminho para os conteúdos da tela do Autorizador
	private static final String REQUISICOES_PENDENTES = "/WEB-INF/partials/requisicoesPendentes.xhtml";
	private static final String TODAS_REQUISICOES = "/WEB-INF/partials/todasRequisicoes.xhtml";
	private static final String REQUISICAO_PENDENTE = "/WEB-INF/partials/requisicaoPendente.xhtml";
	private static final String MINHAS_REQUISICOES = "/WEB-INF/partials/minhasRequisicoes.xhtml";
	private static final String FAZER_REQUISICAO = "/WEB-INF/partials/cadastroRequisicao.xhtml";
	private static final String USUARIOS = "/WEB-INF/partials/usuarios.xhtml";
	private static final String NOVO_USUARIO = "/WEB-INF/partials/novoUsuario.xhtml";
	private static final String MEUS_DADOS = "/WEB-INF/partials/dadosCadastrais.xhtml";
	private static final String USUARIO = "/WEB-INF/partials/usuario.xhtml";
	private static final String ALTERA_SENHA = "/WEB-INF/partials/alteraSenha.xhtml";
	private static final String ALTERA_SENHA_SOLICITANTE = "/WEB-INF/partials/alteraSenhaSolicitante.xhtml";
	
	private Usuario usuarioLogado;
	private String senhaAtual;
	private String novaSenha;
	private String confirmaSenha;
	
	private String conteudo;
	
	private List<Requisicao> requisicoes;
	private Requisicao requisicao;
	private Item item;
	
	private List<Usuario> usuarios;
	private Usuario usuario;
	
	private boolean requisicaoAutorizada;
	private boolean requisicaoRejeitada;
	
	private StreamedContent arquivoRetorno;
	
	public AutorizadorBean() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		usuarioLogado = (Usuario) sessao.getAttribute("usuario");
		RequisicaoDAO dao = new RequisicaoDAO(getEntityManager());
		requisicoes = dao.listaRequiscoes(Status.AGUARDANDO_AUTORIZACAO);
		setConteudo(REQUISICOES_PENDENTES);
	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	public String getSenhaAtual() {
		return senhaAtual;
	}
	
	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
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
	
	public boolean isRequisicaoAutorizada() {
		return requisicaoAutorizada;
	}
	
	public void setRequisicaoAutorizada(boolean requisicaoAutorizada) {
		this.requisicaoAutorizada = requisicaoAutorizada;
	}
	
	public boolean isRequisicaoRejeitada() {
		return requisicaoRejeitada;
	}
	
	public void setRequisicaoRejeitada(boolean requisicaoRejeitada) {
		this.requisicaoRejeitada = requisicaoRejeitada;
	}
	
	
	// Métodos que alteram o conteúdo da tela do Autorizador
	public void mostraRequisicoesPendentes() {
		RequisicaoDAO dao = new RequisicaoDAO(getEntityManager());
		requisicoes = dao.listaRequiscoes(Status.AGUARDANDO_AUTORIZACAO);
		setConteudo(REQUISICOES_PENDENTES);
	}
	
	public void mostraTodasRequisicoes() {
		RequisicaoDAO dao = new RequisicaoDAO(getEntityManager());
		requisicoes = dao.lista();
		setConteudo(TODAS_REQUISICOES);
	}
	
	public void mostraRequisicao(long id) {
		RequisicaoDAO dao = new RequisicaoDAO(getEntityManager());
		requisicao = dao.pesquisa(id);
		setConteudo(REQUISICAO_PENDENTE);
	}
	
	public void mostraMinhasRequisicoes() {
		RequisicaoDAO dao = new RequisicaoDAO(getEntityManager());
		requisicoes = dao.lista(usuarioLogado);
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
	
	public void mostraAlterarSenha() {
		setConteudo(ALTERA_SENHA);
	}
	
	public void mostraAlterarSenhaSolicitante() {
		/*UsuarioDAO dao = new UsuarioDAO(getEntityManager());
		usuario = dao.pesquisa(id);*/
		setConteudo(ALTERA_SENHA_SOLICITANTE);
	}
	
	// Ações da tela
	public void autorizarRequisicao() {
		RequisicaoDAO dao = new RequisicaoDAO(getEntityManager());
		requisicao.setStatus(Status.AUTORIZADA);
		requisicao.setAutorizador((Autorizador) usuarioLogado);
		dao.atualiza(requisicao);
		MessagesUtil.createMessageInfo(null, "Requisição autorizada com sucesso!", null);
	}
	
	public void rejeitarRequisicao() {
		RequisicaoDAO dao = new RequisicaoDAO(getEntityManager());
		requisicao.setStatus(Status.NAO_AUTORIZADA);
		requisicao.setAutorizador((Autorizador) usuarioLogado);
		dao.atualiza(requisicao);
		MessagesUtil.createMessageInfo(null, "Requisição rejeitada!", null);
	}
	
	public void preparaEdicaoRequisicao(long id) {
		RequisicaoDAO dao = new RequisicaoDAO(getEntityManager());
		requisicao = dao.pesquisa(id);
		setConteudo(FAZER_REQUISICAO);
	}
	
	public void excluiRequisicao(long id) {
		RequisicaoDAO dao = new RequisicaoDAO(getEntityManager());
		dao.exclui(dao.pesquisa(id));
		requisicoes = dao.lista(usuarioLogado);
		MessagesUtil.createMessageInfo(null, "Requisição excluída com sucesso!", null);
	}
	
	public void adicionaItemRequisicao() {
		item.setRequisicao(requisicao);
		item.setNumero(requisicao.getItens().size() + 1);
		requisicao.getItens().add(item);
		calculaTotal();
	}

	private void calculaTotal() {
		int j = requisicao.getTotal();
		j = j + item.getQuantidade();
		requisicao.setTotal(j);
		item = new Item();
	}
	
	public void editarItemRequisicao(int i) {
		Item item = requisicao.getItens().get(i);
		System.out.println(item);
		atualizaItens();
	}
	
	public void retirarItemRequisicao(int i) {
		Item item = requisicao.getItens().get(i);
		requisicao.setTotal(requisicao.getTotal() - item.getQuantidade());
		requisicao.getItens().remove(i);
		atualizaItens();
	}
	
	private void atualizaItens() {
		for(int i=0; i<requisicao.getItens().size(); i++) {
			requisicao.getItens().get(i).setNumero(i+1);
		}
	}
	
	public void salvaRequisicao() {
		RequisicaoDAO dao = new RequisicaoDAO(getEntityManager());
		if(requisicao.getItens().size() == 0){
			MessagesUtil.createMessageError(null, "Adicione pelo menos um item!", null);
		}else{
			requisicao.setData(createDataAtual());
			requisicao.setStatus(Status.AUTORIZADA);
			requisicao.setAutorizador((Autorizador) usuarioLogado);
			requisicao.setUsuario(usuarioLogado);
			dao.salva(requisicao);
			MessagesUtil.createMessageInfo(null, "Requisição cadastrada com sucesso!", null);
		}
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
		u.setRejeitado(false);
		//JPAUtil.getEntityManager().getTransaction().begin();
		dao.atualiza(u);
		//JPAUtil.getEntityManager().getTransaction().commit();
		MessagesUtil.createMessageInfo(null, "Usuário autorizado!", null);
	}
	
	public void rejeitarUsuario(long id) {
		UsuarioDAO dao = new UsuarioDAO(getEntityManager());
		Usuario u = dao.pesquisa(id);
		dao.exclui(u);
		usuarios.remove(u);
		MessagesUtil.createMessageInfo(null, "Usuário rejeitado!", null);
	}
	
	public void desativarUsuario(long id) {
		UsuarioDAO dao = new UsuarioDAO(getEntityManager());
		Usuario u = dao.pesquisa(id);
		u.setAtivo(false);
		//JPAUtil.getEntityManager().getTransaction().begin();
		dao.atualiza(u);
		//JPAUtil.getEntityManager().getTransaction().commit();
		MessagesUtil.createMessageInfo(null, "Usuário desativado!", null);
	}
	
	public void preparaEdicaoUsuario(long id) {
		UsuarioDAO dao = new UsuarioDAO(getEntityManager());
		usuario = dao.pesquisa(id);
		setConteudo(USUARIO);
	}
	
	public void salvaUsuario() {
		EntityManager em = JPAUtil.getEntityManager();
		UsuarioDAO dao = new UsuarioDAO(em);
		em.getTransaction().begin();
		try {
			usuario.setAtivo(true);
			if(usuario.getSenha().equals(confirmaSenha)){
				dao.salva(usuario);
				em.getTransaction().commit();
				MessagesUtil.createMessageInfo(null, "Usuário cadastrado com sucesso!", null);
			}else{
				em.getTransaction().rollback();
				MessagesUtil.createMessageError(null, "A confirmação da senha está incorreta!", null);
			}
		} catch (Exception e) {
			em.getTransaction().rollback();
			MessagesUtil.createMessageError(null, "O Siape informado já existe!", null);
		}
	}
	
	public void alteraDadosCadastrais() {
		UsuarioDAO dao = new UsuarioDAO(getEntityManager());
		dao.atualiza(usuarioLogado);
		MessagesUtil.createMessageInfo(null, "Dados alterados com sucesso!", null);
	}
	
	public void alteraDadosDoUsuario() {
		UsuarioDAO dao = new UsuarioDAO(getEntityManager());
		try {
			dao.atualiza(usuario);
			//JPAUtil.getEntityManager().getTransaction().commit();
			MessagesUtil.createMessageInfo(null, "Dados alterados com sucesso!", null);
		} catch (Exception e) {
			//JPAUtil.getEntityManager().getTransaction().rollback();
			MessagesUtil.createMessageError(null, "O Siape informado já existe!", null);
		}
	}
	
	public void alteraSenha() {
		UsuarioDAO dao = new UsuarioDAO(getEntityManager());
		if(usuarioLogado.getSenha().equals(senhaAtual)){
			if(novaSenha.equals(confirmaSenha)){
				usuarioLogado.setSenha(novaSenha);
				dao.atualiza(usuarioLogado);
				MessagesUtil.createMessageInfo(null, "Senha alterada com sucesso!", null);
			}else{
				MessagesUtil.createMessageError(null, "A confirmação da senha está incorreta!", null);
			}
		}else{
			MessagesUtil.createMessageError(null, "A senha atual está incorreta!", null);
		}
		
	}
	
	public void alteraSenhaSolicitante() {
		UsuarioDAO dao = new UsuarioDAO(getEntityManager());
		if(usuario.getSenha().equals(senhaAtual)){
			if(novaSenha.equals(confirmaSenha)){
				usuario.setSenha(novaSenha);
				dao.atualiza(usuario);
				MessagesUtil.createMessageInfo(null, "Senha alterada com sucesso!", null);
			}else{
				MessagesUtil.createMessageError(null, "A confirmação da senha está incorreta!", null);
			}
		}else{
			MessagesUtil.createMessageError(null, "A senha atual está incorreta!", null);
		}
		
	}
	
	
	// Impressão
	public void preparaImpressao(long id) {
		RequisicaoDAO dao = new RequisicaoDAO(getEntityManager());
		requisicao = dao.pesquisa(id);
	}
	
	public StreamedContent getArquivoRetorno() {
		String nomeRelatorioSaida = usuarioLogado.getNome() + "-Requisicao-" + requisicao.getId();
		RelatorioUtil relatorioUtil = new RelatorioUtil();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("solicitante", requisicao.getUsuario().getNome());
		parametros.put("data", requisicao.getData());
		parametros.put("autorizador", requisicao.getAutorizador().getNome());
		try {
			arquivoRetorno = relatorioUtil.geraRelatorio(parametros, nomeRelatorioSaida, requisicao.getItens());
		} catch (UtilException e) {
			MessagesUtil.createMessageError(null, e.getMessage(), null);
		}
		return arquivoRetorno;
	}
	
	public void setArquivoRetorno(StreamedContent arquivoRetorno) {
		this.arquivoRetorno = arquivoRetorno;
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
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute("em");
	}

}
