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
	<c:set var="msg" value="Garota de Programa Java!" />

	<p>Mensagem :${msg}</p>
</body>
</html>