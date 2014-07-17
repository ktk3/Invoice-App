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
                    <a href="positions/${poz.id}" class="btn btn-default">Go to page</a>
                </td>
                <td>
                    <sf:form action="positions/${poz.id}" method="delete">
                        <input type="submit" class="btn btn-danger" value="Delete" />
                    </sf:form>
                </td>
            </tr>
        </c:forEach>
	<tr>
		<td></td>
		<td><div id="has_p"><a href="positions?page=${prev_page}">Previous </a></div></td>
		<td><div id="has_n"><a href="positions?page=${next_page}">Next</a></div></td>
		<td></td>
	</tr>
    </table>
    </div>
    <br />
    <a href="welcome">Go back</a>
</body>
<script type="text/javascript">
window.onload = function() {
var has_next = ${has_next};
var has_prev = ${has_prev};

console.log(has_next);
console.log(has_prev);
if (has_next == 0) {
  document.getElementById("has_n").style.display = 'none';
}
if (has_prev == 0) {
  document.getElementById("has_p").style.display = 'none';
}
 }
</script>
</html>
