<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--@elvariable id="invoice" type="org.invoices.Invoice"--%>
<%--@elvariable id="unlisted" type="java.util.List<org.invoices.Position>"--%>

<html>
<head>
    <title>Invoice page</title>
     <link href="/invoices/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <h2>Invoice info</h2>
    <div id="tabl" class="container">
	<table class="table">
        <sf:form method="post">
                <tr>
                    <td><label for="id">ID:</label></td>
                    <td><input name="id" id="id" value="${invoice.id}" disabled="true"/></td>
                </tr>
                <tr>
                    <td><label for="buyer">Buyer:</label></td>
                    <td><input name="buyer" id="buyer" value="${invoice.buyer}" disabled="true"/></td>
                </tr>
                <tr>
                    <td><label for="seller">Seller:</label></td>
                    <td><input name="seller" id="seller" value="${invoice.seller}" disabled="true" /></td>
                </tr>
                <tr>
                    <td><label for="positions">Positions:</label></td>
                    <td>
                        <table id="positions" class="table">
                            <c:forEach items="${invoice.positions}" var="pos">
                        <tr>
                            <sf:form action="${invoice.id}/positions/${pos.id}" method="delete">
                                <td>
                                    <a href="../positions/${pos.id}" id="href-${pos.id}">${pos.name}</a>
                                </td>
                                <td>
                                    <input type="submit" value="Remove" id="remove-${pos.id}" class="btn btn-danger btn-sm"/>
                                    <script src="/invoices/resources/jquery-1.11.1.js"></script>
                                    <script type="text/javascript">
                                        $("#remove-${pos.id}").on("click", function() {
                                            $("#remove-${pos.id}").addClass("hidden");
                                            $("#href-${pos.id}").remove();
 
                                            // add to list of unlisted
                                            var opt = document.createElement("option");
                                            opt.setAttribute("value", "${pos.id}");
                                            opt.textContent = "${pos.name}";
                                            $("#selected-pos").append(opt);
                                        });
                                    </script>
                                </td>
                            </sf:form>
                        </tr>
                    </c:forEach>
                        </table>
                    </td>
                    
                </tr> 
                <tr>
                <label for="unlisted">Unlisted:</label>
                <table id="unlisted" class="table">
                    <tr>
                        <sf:form method="put" id="add-form">
                            <td>
                                <select id="selected-pos" class="form-control">
                                    <c:forEach items="${unlisted}" var="upos">
                                        <option value="${upos.id}">
                                            ${upos.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <input type="submit" value="Add" id="add-position" class="btn btn-success btn-sm"/>
                                <script src="/invoices/resources/jquery-1.11.1.js"></script>
                                <script type="text/javascript">
                                    $("#add-position").on("click", function() {
                                        $("#selected-pos").selected().remove();
                                    });
                                </script>
                            </td>
                        </sf:form>
                    </tr>
                </table>
            </tr>   
                <tr>
                    <td><input type="button" value="Edit" id="edit" class="btn btn-primary"/></td>
                    <td><input type="submit" value="Save" id="save" class="btn btn-primary hide"/></td>
                </tr>
                
        </sf:form>
	</table>
    </div>
 
    <br /><br />
    <a href="../invoices">Go Back</a>
 
    <script src="/invoices/resources/jquery-1.11.1.js"></script>
    <script>
            (function() {
            // prepare default form action
            setAddAction();
 
            // handler for changing action
            $("#selected-pos").on("change", function() {
                setAddAction();
            });
 
            function setAddAction() {
                var id = $("#selected-pos").val();
                $("#add-form").attr("action", "${invoice.id}/invoices/" + id);
            }
        })();
        (function() {
            $("#edit").on("click", function() {
                $("#edit").addClass("hide");
 
                // enable stuff
                $("#buyer").removeAttr("disabled");
                $("#seller").removeAttr("disabled");
                $("#save").removeClass("hide");

            });
        })();
    </script>
</body>
</html>
