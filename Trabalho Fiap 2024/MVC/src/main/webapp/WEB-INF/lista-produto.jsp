<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Produtos</title>
</head>
<body>
	<div class="container">
		<h1>Produtos</h1>
		<table>
			<tr>
				<th>Nome</th>
				<th>Quantidade</th>
				<th>Valor</th>
				<th>Data de Fabricação</th>
			</tr>
			<c:forEach items="${produtos}" var="p">
				<tr>
					<td>${p.nome}</td>
					<td>${p.quantidade}</td>
					<td>${p.valor}</td>
					<td><fmt:formatDate value="${p.dataFabricacao.time}"
							pattern="dd/MM/yyyy" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
