<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.cineverse.model.bean.FilmBean" %>
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
	<%
		List<FilmBean> films = (List<FilmBean>) request.getAttribute("films");
	
		for(FilmBean f : films){
	%>
	
		<p style="color:black"><%= f %></p>
		
	<%} %>
	
	<p>
</body>
</html>