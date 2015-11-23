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
import br.com.lam.model.Item;
import br.com.lam.model.Requisicao;
import br.com.lam.model.Status;
import br.com.lam.model.Usuario;
import br.com.lam.util.MessagesUtil;
import br.com.lam.util.RelatorioUtil;

@ManagedBean
@SessionScoped
public class SolicitanteBean {
	
	// Caminho para os conteúdos da tela do Autorizador
	private static final String MINHAS_REQUISICOES = "/WEB-INF/partials/minhasRequisicoes.xhtml";
	private static final String REQUISICAO = "/WEB-INF/partials/requisicao.xhtml";
	private static final String FAZER_REQUISICAO = "/WEB-INF/partials/cadastroRequisicao.xhtml";
	private static final String MEUS_DADOS = "/WEB-INF/partials/dadosCadastrais.xhtml";
	private static final String ALTERA_SENHA = "/WEB-INF/partials/alteraSenha.xhtml";
	
	private Usuario usuarioLogado;
	private String novaSenha;
	private String senhaAtual;
	
	private String conteudo;
	
	private List<Requisicao> requisicoes;
	private Requisicao requisicao;
	private Item item;
	
	private String confirmaSenha;
	
	private StreamedContent arquivoRetorno;
	
	public SolicitanteBean() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		usuarioLogado = (Usuario) sessao.getAttribute("usuario");
		RequisicaoDAO dao = new RequisicaoDAO(getEntityManager());
		requisicoes = dao.lista(usuarioLogado);
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
	
	public String getSenhaAtual() {
		return senhaAtual;
	}
	
	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
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
		RequisicaoDAO dao = new RequisicaoDAO(getEntityManager());
		requisicao = dao.pesquisa(id);
		setConteudo(REQUISICAO);
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
	
	public void mostraMeusDados() {
		setConteudo(MEUS_DADOS);
	}
	
	public void mostraAlterarSenha() {
		setConteudo(ALTERA_SENHA);
	}
	
	
	// Ações da tela
	public void preparaEdicaoRequisicao(long id) {
		RequisicaoDAO dao = new RequisicaoDAO(getEntityManager());
		requisicao = dao.pesquisa(id);
		setConteudo(FAZER_REQUISICAO);
	}
	
	public void excluiRequisicao(long id) {
		RequisicaoDAO dao = new RequisicaoDAO(getEntityManager());
		//JPAUtil.getEntityManager().getTransaction().begin();
		dao.exclui(dao.pesquisa(id));
		//JPAUtil.getEntityManager().getTransaction().commit();
		requisicoes = dao.lista(usuarioLogado);
		MessagesUtil.createMessageInfo(null, "Requisição excluída com sucesso!", null);
	}
	
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
		if(requisicao.getItens().size() == 0){
			MessagesUtil.createMessageError(null, "Adicione pelo menos um item!", null);
		}else{
			//JPAUtil.getEntityManager().getTransaction().begin();
			requisicao.setData(createDataAtual());
			requisicao.setStatus(Status.AGUARDANDO_AUTORIZACAO);
			requisicao.setUsuario(usuarioLogado);
			dao.salva(requisicao);
			//JPAUtil.getEntityManager().getTransaction().commit();
			MessagesUtil.createMessageInfo(null, "Requisição cadastrada com sucesso!", null);
		}
	}
	
	public void alteraDadosCadastrais() {
		UsuarioDAO dao = new UsuarioDAO(getEntityManager());
		//EntityTransaction et = getEntityManager().getTransaction();
		//et.begin();
		try {
			dao.atualiza(usuarioLogado);
			//et.commit();
			MessagesUtil.createMessageInfo(null, "Dados alterados com sucesso!", null);
		} catch (Exception e) {
			//et.rollback();
			MessagesUtil.createMessageError(null, "O Siape informado já existe!", null);
		}
	}
	
	public void alteraSenha() {
		//JPAUtil.getEntityManager().getTransaction().begin();
		UsuarioDAO dao = new UsuarioDAO(getEntityManager());
		if(usuarioLogado.getSenha().equals(senhaAtual)){
			if(novaSenha.equals(confirmaSenha)){
				usuarioLogado.setSenha(novaSenha);
				dao.atualiza(usuarioLogado);
				//JPAUtil.getEntityManager().getTransaction().commit();
				MessagesUtil.createMessageInfo(null, "Senha alterada com sucesso!", null);
			}else{
				//JPAUtil.getEntityManager().getTransaction().rollback();
				MessagesUtil.createMessageError(null, "A confirmação da senha está incorreta!", null);
			}
		}else{
			//JPAUtil.getEntityManager().getTransaction().rollback();
			MessagesUtil.createMessageError(null, "A senha atual está incorreta!", null);
		}
		
	}
	
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
