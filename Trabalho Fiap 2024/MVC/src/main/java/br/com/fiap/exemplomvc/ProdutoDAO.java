package br.com.fiap.exemplomvc;

import java.util.ArrayList;
import java.util.Calendar;

import br.com.fiap.exemplomvc.bean.Produto;

public class ProdutoDAO { 
    private ArrayList<Produto> produtos = new ArrayList<Produto>();

    public ProdutoDAO() {  
        this.produtos.add(new Produto(1, "Play5", 5000.00f, Calendar.getInstance(), 5));
        this.produtos.add(new Produto(2, "Play3", 650.00f, Calendar.getInstance(), 10));
    }

    public ArrayList<Produto> listar() {
        return this.produtos;
    }
}
