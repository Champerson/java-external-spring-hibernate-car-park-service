<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

        <title><spring:message code="title.admin.menu.page" /></title>
    </head>
    <body>
        <jsp:include page="components/navigation-top-form.jsp"/>
        <center><h4><spring:message code="header.admin.menu.page" /></h4><br></center>
        <div class="mx-auto" style="width: 300px">
            <a href="${pageContext.request.contextPath}/user/users" class="btn btn-outline-primary" style="width:100%">
                <spring:message code="button.get.all.users" />
            </a>
            <a href="${pageContext.request.contextPath}/bus/buses" class="btn btn-outline-primary mt-2" style="width:100%">
                <spring:message code="button.get.all.buses" />
            </a>
            <a href="${pageContext.request.contextPath}/route/routes" class="btn btn-outline-primary mt-2" style="width:100%">
                <spring:message code="button.get.all.routes" />
            </a>
        </div>
    </body>
</html>
