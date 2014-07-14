<%--@elvariable id="invoice" type="org.invoices.Invoice"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<html>
<head>
    <title>Cannot delete invoice</title>
    <link href="/invoices/resources/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
    Oops! Resource <a href="${invoice.id}">${invoice.id}</a> can not be deleted.
 
    <br /><br /><br />
    <a href="../welcome">Back to main page.</a>
</body>
</html>