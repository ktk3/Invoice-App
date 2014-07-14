<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
	<title>Invoices app opn Spring</title>
</head>
<body>
	<h1>Welcome to the Invoices App on Spring!</h1>

	<ul>
		<li><a href="positions">List positions</a></li>
		<li><a href="invoices">List invoices</a></li>
	</ul>

	Today is: <fmt:formatDate value="${today}" pattern="dd-MM-yyyy" />
</body>
</html>
