package br.com.fiap.exemplomvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import br.com.fiap.exemplomvc.bean.Produto;
import br.com.fiap.exemplomvc.dao.ProdutoDAO;

@WebServlet("/produtoLista")
public class ProdutoListarController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProdutoDAO dao;

	@Override
	public void init() throws ServletException {
		super.init();
		dao = new ProdutoDAO();
	}

	public ProdutoListarController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Produto> lista = dao.lista();
		request.setAttribute("produtos", lista);
		request.getRequestDispatcher("lista-produto.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
