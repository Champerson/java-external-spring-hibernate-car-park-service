<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="routeForm" value="${routeForm}" scope="request"/>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

        <title><spring:message code="title.admin.routes.page" /></title>
    </head>
    <body>
        <center><h4><spring:message code="header.admin.route" /></h4></center></br>
        <jsp:include page="components/route-form.jsp" >
            <jsp:param name="link" value="${pageContext.request.contextPath}/route/create" />
        </jsp:include>
    </body>
</html>
