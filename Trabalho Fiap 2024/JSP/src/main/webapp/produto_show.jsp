<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="br.com.fiap.entities.Produto" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>duda javeta</title>
</head>
<body>
<%
    Produto p = (Produto) session.getAttribute("produto");

    if (p != null) {
        out.println("Nome do produto: " + p.getNome() + "<br>");
        out.println("Valor do produto: " + p.getValor());
    } else {
        out.println("Produto não encontrado na sessão.");
    }
%>
</body>
</html>
