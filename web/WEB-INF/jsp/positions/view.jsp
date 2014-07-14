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
                    <th><label for="name">Name:</label></th>
                    <th><input name="name" id="name" value="${position.name}" disabled="true"/></th>
                </tr>
                <tr>
                    <th><label for="price">Price:</label></th>
                    <th><input name="price" id="price" value="${position.price}" disabled="true" /></th>
                </tr>
                <tr>
                    <th><input type="button" value="Unlock" id="unlock" /></th>
                    <th><input type="submit" value="Save" id="save" class="hidden" /></th>
                </tr>
        </sf:form>
	</table>
    </div>
 
    <br /><br />
    <a href="../positions">Go Back</a>
 
    <script src="/invoices/resources/jquery-1.11.1.js"></script>
    <script>
        (function() {
            $("#unlock").on("click", function() {
                $("#unlock").addClass("hidden");
 
                // enable stuff
                $("#name").removeAttr("disabled");
                $("#price").removeAttr("disabled");
                $("#save").removeClass("hidden");
            });
        })();
    </script>
</body>
</html>
