<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
 
<html>
<head>
    <title>Position page</title>
     <link href="/invoices/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <h2>Position info</h2>
    <div id="tabl" class="container">
	<table class="table">
        <sf:form method="post">
                <tr>
                    <td><label for="name">Name:</label></td>
                    <td><input name="name" id="name" value="${position.name}" disabled="true"/></td>
                </tr>
                <tr>
                    <td><label for="price">Price:</label></td>
                    <td><input name="price" id="price" value="${position.price}" disabled="true" /></td>
                </tr>
                <tr>
                    <td><input type="button" value="Edit" id="edit" class="btn btn-primary"/></td>
                    <td><input type="submit" value="Save" id="save" class="btn btn-primary hide"/></td>
                </tr>
        </sf:form>
	</table>
    </div>
 
    <br /><br />
    <a href="../positions">Go Back</a>
 
    <script src="/invoices/resources/jquery-1.11.1.js"></script>
    <script>
        (function() {
            $("#edit").on("click", function() {
                $("#edit").addClass("hide");
 
                // enable stuff
                $("#name").removeAttr("disabled");
                $("#price").removeAttr("disabled");
                $("#save").removeClass("hide");
            });
        })();
    </script>
</body>
</html>
