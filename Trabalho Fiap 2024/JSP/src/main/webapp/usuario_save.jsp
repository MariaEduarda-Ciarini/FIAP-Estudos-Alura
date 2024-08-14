<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="br.com.fiap.entities.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Salvando usuário</title>
</head>
<body>
	<jsp:include page="usuario_edit.jsp" />
	<%
if (request.getParameter("nome") !=null
							&& request.getParameter("idade") != null) {
							String nome = request.getParameter("nome");
							byte idade = Byte.parseByte (request.getParameter("idade"));
							
							Usuario u = new Usuario(nome, idade);
							
							application.setAttribute("usuario", u);
							out.println("Usuário armazenado!");
							}%>
</body>
</html>