<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.text.SimpleDateFormat, java.util.Date"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Exemplo JSTL5</title>
</head>
<body>
	<%
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
	String dateInString = "2001-11-01 9:00:00";
	Date date = sdf.parse(dateInString);
	%>

	<c:set var="data" value="<%=date%>" />

	<p>
		Data Formatada (1):
		<fmt:formatDate type="time" value="${data}" />
	</p>
	<p>
		Data Formatada (2):
		<fmt:formatDate type="date" value="${data}" />
	<p>
		Data Formatada (3):
		<fmt:formatDate type="both" value="${data}" />
	</p>

	<p>
		Data Formatada (4):
		<fmt:formatDate pattern="y" value="${data}" />
	</p>
</body>
</html>