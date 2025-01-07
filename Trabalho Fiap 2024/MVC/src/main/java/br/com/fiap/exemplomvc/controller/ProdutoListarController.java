package br.com.fiap.exemplomvc.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.exemplomvc.ProdutoDAO; // Correção no nome da classe
import br.com.fiap.exemplomvc.bean.Produto;

@WebServlet("/ProdutoListarController")
public class ProdutoListarController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ProdutoDAO dao; 

    @Override
    public void init() throws ServletException {
        super.init();
        dao = new ProdutoDAO(); 
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ArrayList<Produto> lista = dao.listar();
            request.setAttribute("produtos", lista);
            request.getRequestDispatcher("lista-produto.jsp").forward(request, response);
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
