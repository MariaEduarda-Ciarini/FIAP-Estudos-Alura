<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Exemplo JSTL4</title>
</head>
<body>

	<c:set var="nome" value="Ciarini Ciarini" />
	<c:set var="idade" value="20" />

	<c:out value="${idade}" />

	<c:url value="enviarNome.jsp" var="link">
		<c:param name="nome" value="${nome}" />
	</c:url>
	<a href="${link}">Enviando nome</a>
</body>
</html>