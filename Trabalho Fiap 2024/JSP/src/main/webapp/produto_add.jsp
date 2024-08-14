<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="br.com.fiap.entities.Produto" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Adicionar Produto</title>
</head>
<body>
    <%
    String nome = request.getParameter("nome");
    float valor = 0;

    try {
        valor = Float.parseFloat(request.getParameter("valor"));
    } catch (NumberFormatException e) {
        out.println("Erro ao converter o valor. Certifique-se de que está inserindo um número válido.");
    }

    if (nome != null && !nome.isEmpty() && valor != 0) {
        Produto p = new Produto(nome, valor);
        session.setAttribute("produto", p);
        out.println("Produto adicionado com sucesso: " + p.getNome() + " - Valor: " + p.getValor());
    } else {
        out.println("Nome do produto ou valor inválidos.");
    }
    %>
</body>
</html>
