<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
 
<html>
<head>
    <title>Invoices</title>
    <link href="/invoices/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <h1>List of invoices</h1>
    <input type="button" class="btn btn-primary btn-lg" value="Add new invoice" href="invoices?new">
    <div class="container">
    <table class="table table-striped">
        <tr>
            <th>Id</th>
            <th>Buyer</th>
            <th>Seller</th>
            <th>Positions</th>
	        <th>Details</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="#{invoices}" var="poz">
            <tr>
                <td>${poz.id}</td>
                <td>${poz.buyer}</td>
		        <td>${poz.seller}</td>
                <td>
                    <a href="invoices/${poz.id}" class="btn btn-default">Go to page</a>
                </td>
                <td>
                    <sf:form action="invoices/${poz.id}" method="delete">
                        <input type="submit" class="btn btn-danger" value="Delete" />
                    </sf:form>
                </td>
            </tr>
        </c:forEach>
    </table>
    </div>
    <br />
    <a href="welcome">Go back</a>
</body>
</html>
