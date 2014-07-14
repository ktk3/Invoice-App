<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
 
<html>
<head>
    <title>Positions</title>
    <link href="/invoices/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <h1>List of positions</h1>
    <input type="button" class="btn btn-primary btn-lg" value="Add new position" onclick="location.href='positions?new';">
    <div class="container">
    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Details</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="#{positions}" var="poz">
            <tr>
                <td>${poz.name}</td>
                <td>${poz.price}</td>
                <td>
                    <a href="positions/${poz.id}">Go to page</a>
                </td>
                <td>
                    <sf:form action="positions/${poz.id}" method="delete">
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
