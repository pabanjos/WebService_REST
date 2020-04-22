<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="port.ServletConfiguracao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<link rel="icon" href="icon.png" type="image/x-icon">
	<title>Configuração</title>
</head>
<body>
	<%
		Map<String, String> bytes = ServletConfiguracao.obterBytesImagens();
			if (!bytes.isEmpty()) {
		for (Map.Entry<String, String> item : bytes.entrySet()) {
	%>
	<figure style="float: left;">
		<img alt="teste" src="data:image/png;base64,<%= item.getValue() %>" width="100px">
		<figcaption><%= item.getKey() %></figcaption>
	</figure> 
	<%
			};
		} else {
	%>
	<form id="form" method="post" action="configuracao">
		<input type="text" name="caminho" id="caminho" placeholder="caminho..">
		<button type="submit">selecionar</button>
	</form>
	<%
		}
	%>
</body>
</html>
