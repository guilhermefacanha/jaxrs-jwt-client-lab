<%@page import="lab.entity.enumeration.MsgType"%>
<%@page import="lab.entity.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Rest JWT - Client</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<%
	MsgType msgType = null;
	String msg = (String) request.getAttribute("msg");

	try {
		msgType = (MsgType) request.getAttribute("msg_type");
	} catch (Exception e) {
	}

	boolean hasMsg = msg != null && !msg.isEmpty() && msgType != null;
%>
<body>
	<%
		if (hasMsg) {

			if (msgType.equals(MsgType.ERRO)) {
	%>
	<div class="error">
		<%
			} else if (msgType.equals(MsgType.OK)) {
		%>
		<div class="success">
			<%
				} else if (msgType.equals(MsgType.WARN)) {
			%>
			<div class="warning">
				<%
					} else if (msgType.equals(MsgType.INFO)) {
				%>
				<div class="info">
					<%
						}
					%>

					<%=msg%>
				</div>
				<%
					}
				%>
			
</body>
</html>