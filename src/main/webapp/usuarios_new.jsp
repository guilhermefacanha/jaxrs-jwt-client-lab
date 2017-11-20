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

	<h3>New User</h3>
	<form class="form-style-9" action="newuser.do">
		<ul>
			<li>
				<input type="text" id="user" name="user" class="field-style field-split align-left" placeholder="User" style="width: 200px;"/>
			</li>
			<li>
				<input type="submit" value="Save" />
			</li>
		</ul>
	</form>
</body>
</html>