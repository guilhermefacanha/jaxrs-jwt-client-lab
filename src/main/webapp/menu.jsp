<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<nav>
	<ul>
		<li>
			<a href="index.jsp">Home</a>
		</li>
		<li>
			<a href="login.jsp">Login</a>
		</li>
		<li>
			<a href="usuarios.jsp">Usu�rios</a>
		</li>
		<li>
			<a href="usuarios_new.jsp">Novo Usu�rio</a>
		</li>
	</ul>
	</nav>
	<jsp:include page="/resultado.jsp" />
</body>
</html>