package br.com.lam.util;

import java.util.List;

import br.com.lam.dao.RequisicaoDAO;
import br.com.lam.model.Requisicao;
import br.com.lam.model.Status;
import br.com.lam.model.Usuario;

public class Main {
	
	public static void main(String[] args) {
		
		Usuario u = new Usuario();
		u.setId(2);
		List<Requisicao> requisicoes = new RequisicaoDAO(JPAUtil.getEntityManager()).lista(u);
		
		for(Requisicao r : requisicoes){
			System.out.println(r.getId());
		}
		
	}

}
