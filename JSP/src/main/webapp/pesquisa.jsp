<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tratamento de pesquisa</title>
</head>
<body>
	Participação na pesquisa: ${param.pesquisa}

	<br> Nome: ${param.nome}
	
	<%
	    String idadeStr = request.getParameter("idade");
	    String salarioStr = request.getParameter("salario");
	    int idade = 0;
	    double salario = 0.0;

	    if (idadeStr != null && !idadeStr.isEmpty()) {
	        try {
	            idade = Integer.parseInt(idadeStr);
	        } catch (NumberFormatException e) {
	            idade = 0; // valor padrão em caso de erro de conversão
	        }
	    }

	    if (salarioStr != null && !salarioStr.isEmpty()) {
	        try {
	            salario = Double.parseDouble(salarioStr);
	        } catch (NumberFormatException e) {
	            salario = 0.0; // valor padrão em caso de erro de conversão
	        }
	    }

	    session.setAttribute("nome", request.getParameter("nome"));
	%>

	<br> Idade: <%= idade %>
	<br> Maior de idade?: <%= idade >= 19 ? "Sim" : "Não" %>
	<br> Salário: <%= salario * 12 %>
	<br> Observações: ${param.obs}
	<br>

	Nome na sessão: ${sessionScope.nome}
</body>
</html>
