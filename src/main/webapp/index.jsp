<%@page import="lab.service.RestService"%>
<%@page import="lab.entity.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Rest JWT - Client</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<%
	User sessionUser = (User) session.getAttribute("usuario_logado");
	String url = RestService.URL_BASE;
	boolean logado = sessionUser != null && sessionUser.isValido();
%>
<body>
	<jsp:include page="/menu.jsp" />
	<div class="info">
		<%
			if (!logado) {
		%>
		Olá, bem vindo, efetue o login para realizar operações que exigem
		autenticação!
		<%
			} else {
		%>
		Olá, bem vindo,
		<%=sessionUser.getName()%>
		<%
			}
		%>
	</div>

	<form class="form-style-9" action="config.do">
		<ul>
			<li>
				<input type="text" id="url" name="url" value="<%=url %>" class="field-style field-split align-left" placeholder="Base Rest Url" style="width: 300px;"/>
			</li>
			<li>
				<input type="submit" value="Change Base URL" />
			</li>
		</ul>
	</form>
</body>
</html>