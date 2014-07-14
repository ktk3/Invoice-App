<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new invoice</title>
    <link href="/invoices/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <h2>Add new invoice</h2>
    <div id="tabl" class="container">
    <table class="table">
        <sf:form method="post" action="invoices">
                <tr>
                    <th><label for="buyer">Buyer:</label></th>
                    <th><input name="buyer" id="buyer" value="${invoice.buyer}"/></th>
                </tr>
                <tr>
                    <th><label for="seller">Seller:</label></th>
                    <th><input name="seller" id="seller"
                           value="${invoice.seller}" /></th>
                </tr>
                <tr>
                    <th><input type="submit" value="Save" id="save" /></th>
                </tr>

        </sf:form>
    </table>
    </div>
 
    <br /><br />
    <a href="invoices">Go Back</a>
</body>
</html>