<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
	<title>Invoices app opn Spring</title>
	<link href="/invoices/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<h1>Welcome to the Invoices App on Spring!</h1>

	<ul>
		<li><a href="positions" class="btn btn-default btn-lg">List positions</a></li>
		<li><a href="invoices" class="btn btn-default btn-lg">List invoices</a></li>
	</ul>

	Today is: <fmt:formatDate value="${today}" pattern="dd-MM-yyyy" />
</body>
</html>
