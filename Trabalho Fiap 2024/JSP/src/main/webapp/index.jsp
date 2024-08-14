<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Action</title>
</head>
<body>
	<jsp:forward page="usuario_edit.jsp">
			<jsp:param name="nome" value="VulgoMalvadao"/>
			<jsp:param name="idade" value="50"/>
	</jsp:forward>
</body>
</html>