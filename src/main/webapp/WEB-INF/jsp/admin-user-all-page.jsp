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

        <title><spring:message code="title.admin.users.page" /></title>
    </head>
    <body>
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success" role="alert">
                <spring:message code="${successMessage}" />
            </div>
        </c:if>

        <center><h4><spring:message code="header.admin.all.users.page" /></h4></center></br>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col"><spring:message code="label.user.login" /></th>
                    <th scope="col"><spring:message code="label.user.name" /></th>
                    <th scope="col"><spring:message code="label.user.role" /></th>
                    <th scope="col"><spring:message code="label.user.assigned.route" /></th>
                    <th scope="col"><spring:message code="label.user.open.details" /></th>
                    <th scope="col"><spring:message code="label.user.delete" /></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${users}" var="user" varStatus="loop">
                    <tr>
                        <th scope="row">${loop.count}</th>
                        <td>${user.login}</td>
                        <td>${user.name}</td>
                        <td>
                            <c:if test="${user.accessRole == 'ROLE_DRIVER'}"><spring:message code="label.user.role.driver" /></c:if>
                            <c:if test="${user.accessRole == 'ROLE_ADMIN'}"><spring:message code="label.user.role.admin" /></c:if>
                        </td>
                        <td>${user.assignment.route.number}</td>
                        <td>
                            <form name="user-details" action="${pageContext.request.contextPath}/user/details" method="get">
                                <input name="userId" type="hidden" value="${user.id}">
                                <button class="btn btn-primary"><spring:message code="button.details" /></button>
                            </form>
                        </td>
                        <td>
                            <form name="delete-user" action="${pageContext.request.contextPath}/user/delete" method="post">
                                <input name="userId" type="hidden" value="${user.id}">
                                <button class="btn btn-danger">X</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
