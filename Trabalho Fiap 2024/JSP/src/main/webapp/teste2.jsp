<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Exemplo JSTL</title>
</head>
<body>
	<c:set var="idade" value="20" />


	<c:if test="${idade >= 18  }">
		<p>Já está podendo!</p>
	</c:if>

	<c:if test="${not empty idade}">
		<p>${idade}</p>
	</c:if>

</body>
</html>