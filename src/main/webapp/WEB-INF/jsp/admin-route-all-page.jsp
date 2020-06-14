<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

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
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success" role="alert">
                <spring:message code="${successMessage}" />
            </div>
        </c:if>

        <center><h4><spring:message code="header.admin.all.routes.page" /></h4></center></br>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col"><spring:message code="label.route.number" /></th>
                    <th scope="col"><spring:message code="label.route.description" /></th>
                    <th scope="col"><spring:message code="label.route.open.details" /></th>
                    <th scope="col"><spring:message code="label.route.delete" /></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${routes}" var="route" varStatus="loop">
                    <tr>
                        <th scope="row">${loop.count}</th>
                        <td>${route.number}</td>
                        <td>${route.descriptionEn}</td>
                        <td>
                            <form name="edit-route" action="${pageContext.request.contextPath}/route/details" method="get">
                                <input name="routeId" type="hidden" value="${route.id}">
                                <button class="btn btn-primary"><spring:message code="button.details" /></button>
                            </form>
                        </td>
                        <td>
                            <form name="delete-route" action="${pageContext.request.contextPath}/route/delete" method="post">
                                <input name="routeId" type="hidden" value="${route.id}">
                                <button class="btn btn-danger">X</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="mx-auto" style="width: 300px">
            <form name="add-new-route" action="${pageContext.request.contextPath}/route/create" method="get">
                <button class="btn btn-success" style="width: 300px"><spring:message code="button.route.create" /></button>
            </form>
        </div>
    </body>
</html>