<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Exemplo JSTL3</title>
</head>
<body>
	<c:set var="idade" value="95" />

	<c:choose>
		<c:when test="${idade >= 60 }">
			<p>VELHOTE</p>
		</c:when>
		<c:when test="${idade >= 20}">
			<p>Adulto</p>
		</c:when>
		<c:when test="${idade >= 15}">
			<p>KID</p>
		</c:when>
		<c:otherwise>
			<p>Infancia</p>
		</c:otherwise>
	</c:choose>


	<c:if test="${not empty idade}">
		<p>${idade}</p>
	</c:if>

</body>
</html>