package br.com.fiap.exemplomvc.dao;

import java.util.ArrayList;
import java.util.Calendar;

import br.com.fiap.exemplomvc.bean.*;

public class ProdutoDAO {
	private ArrayList<Produto> produtos = new ArrayList<Produto>();

	public ProdutoDAO() {
		this.produtos.add(new Produto(1, "Batman preto", 40.20f, Calendar.getInstance(), 5));

		this.produtos.add(new Produto(2, "Batman rosinha", 40.20f, Calendar.getInstance(), 10));
	}

	public ArrayList<Produto> lista() {
		return this.produtos;
	}

}
