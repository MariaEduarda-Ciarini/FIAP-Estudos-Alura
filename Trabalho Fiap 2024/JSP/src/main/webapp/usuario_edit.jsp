<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="br.com.fiap.entities.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>usuarioeditjsp</title>
</head>
<body>
<jsp:include page="usuario_save.jsp"/>
<%
Usuario u = (Usuario) application.getAttribute("usuario");%>
<form method="POST" action="usuario_save.jsp">
		<label>Nome: </label>
		<input type="text" name="nome" value="<%=u.getNome()%>">
		<br>
		<label>Idade: </label>
		<input type="text" name="idade" value="<%=u.getIdade() %>">
		<br><br>
		<input type="submit" value="Atualizar">
		</form>

</body>
</html>