<%@page import="lab.service.RestService"%>
<%@page import="lab.entity.User"%>
<%@page import="java.util.List"%>
<%@page import="lab.service.UserService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Rest Get Test</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/tables-min.css">
</head>
<%
	String jsonResult = "";
	try {
		jsonResult = request.getAttribute("res").toString();
	} catch (Exception e) {
	}
%>
<body>

	<jsp:include page="/menu.jsp" />

	<h3>Users</h3>
	<form class="form-style-9" action="restget.do" method="post">
		<ul>
			<li><label>Base Rest URL: <%=RestService.URL_BASE%></label></li>
		</ul>
		<ul>
			<li>Path:</li>
			<li><input type="text" id="path" name="path" class="field-style field-split align-left" placeholder="users/all" style="width: 300px;" /></li>
		</ul>
		<ul>
			<li>Repeat:</li>
			<li><input type="text" id="rep" name="rep" class="field-style field-split align-left" value="0" style="width: 300px;" /></li>
		</ul>
		<ul>
			<li>Random end Param:</li>
			<li><input type="text" id="p1" name="p1" class="field-style field-split align-left" value="0" style="width: 50px;" /></li>
			<li><input type="text" id="p2" name="p2" class="field-style field-split align-left" value="0" style="width: 50px;" /></li>
		</ul>
		<ul>
			<li><input type="submit" value="Submit Test" /></li>
		</ul>
	</form>

	<br />
	<h3>Result:</h3>
	<pre class="form-style-9 code">
		<code>
			<p>Time: <%=request.getAttribute("time") %></p>
			<p id="result"> </p>
		</code>
	</pre>
</body>
<script>
	var myjson =
<%=jsonResult%>
	document.getElementById('result').innerHTML = JSON.stringify(myjson, null,
			4);
</script>
</html>