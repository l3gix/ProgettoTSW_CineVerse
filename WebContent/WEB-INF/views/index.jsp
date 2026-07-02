<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>CineVerse</title>
<link rel="stylesheet" href="style/navbar.css">
</head>
<body>
	<%@include file="navbar.jsp" %>
	<p>hello</p>
	<a href="<%= request.getContextPath()%>/WelcomeLogin">Login</a>
	<a href="<%= request.getContextPath()%>/WelcomeRegister">Register</a>
</body>
</html>