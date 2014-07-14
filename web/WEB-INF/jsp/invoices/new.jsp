<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new position</title>
    <link href="/invoices/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <h2>Add new position</h2>
    <div id="tabl" class="container">
    <table class="table">
        <sf:form method="post" action="positions">
                <tr>
                    <th><label for="name">Name:</label></th>
                    <th><input name="name" id="name" value="${position.name}"/></th>
                </tr>
                <tr>
                    <th><label for="price">Price:</label></th>
                    <th><input name="price" id="price"
                           value="${position.price}" /></th>
                </tr>
                <tr>
                    <th><input type="submit" value="Save" id="save" /></th>
                </tr>

        </sf:form>
    </table>
    </div>
 
    <br /><br />
    <a href="positions">Go Back</a>
</body>
</html>