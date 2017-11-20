<%@page import="lab.entity.User"%>
<%@page import="java.util.List"%>
<%@page import="lab.service.UserService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/tables-min.css">
</head>
<%
	UserService service = new UserService();
	List<User> lista = service.getUsuarios();
%>
<body>
	<jsp:include page="/menu.jsp" />

	<h3>Users</h3>
	<form class="form-style-9" action="login.do">
		<table class="pure-table pure-table-horizontal">
			<thead>
				<tr>
					<th>#</th>
					<th>User</th>
				</tr>
			</thead>

			<tbody>
				<%for(int i=0;i<lista.size();i++){ %>
				<tr>
					<td><%=(i+1)%></td>
					<td><%=lista.get(i).getName()%></td>
				</tr>
				<%} %>
			</tbody>
		</table>
	</form>
</body>
</html>