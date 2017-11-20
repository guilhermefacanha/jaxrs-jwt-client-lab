<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<jsp:include page="/menu.jsp" />

	<h3>Realizar Login</h3>
	<form class="form-style-9" action="login.do">
		<ul>
			<li>
				<input type="text" id="user" name="user" class="field-style field-split align-left" placeholder="User" />
				<input type="password" id="pass" name="pass" class="field-style field-split align-right" placeholder="Password" />
			</li>
			<li>
				<input type="submit" value="Authenticate" />
			</li>
		</ul>
	</form>
</body>
</html>