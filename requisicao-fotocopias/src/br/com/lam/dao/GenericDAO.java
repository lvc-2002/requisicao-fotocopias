package br.com.lam.dao;

import java.util.List;

public interface GenericDAO<Entity> {
	
	public void salva(Entity e);
	public Entity pesquisa(long id);
	public void exclui(Entity e);
	public void atualiza(Entity e);
	public List<Entity> lista();

}
