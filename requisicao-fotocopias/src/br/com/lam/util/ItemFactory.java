package br.com.lam.util;

import java.util.ArrayList;
import java.util.List;

import br.com.lam.model.Item;

public class ItemFactory {
	
	public static List<Item> load() {
		List<Item> itens = new ArrayList<Item>();
		itens.add(new Item(0, "Teste1", 10));
		itens.add(new Item(0, "Teste2", 20));
		itens.add(new Item(0, "Teste3", 30));
		itens.add(new Item(0, "Teste4", 40));
		return itens;
	}
	
}
