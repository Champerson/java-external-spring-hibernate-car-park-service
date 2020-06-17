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

        <title><spring:message code="title.admin.routes.page" /></title>
    </head>
    <body>
        <jsp:include page="components/navigation-top-form.jsp"/>
        <center><h4><spring:message code="header.admin.route.assign.driver" /></h4></center></br>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col"><spring:message code="label.user.login" /></th>
                    <th scope="col"><spring:message code="label.user.name" /></th>
                    <th scope="col"><spring:message code="label.user.phone" /></th>
                    <th scope="col"><spring:message code="label.user.email" /></th>
                    <th scope="col"><spring:message code="label.user.assign" /></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${users}" var="user" varStatus="loop">
                    <tr>
                        <th scope="row">${loop.count}</th>
                        <td>${user.login}</td>
                        <td>${user.name}</td>
                        <td>${user.phone}</td>
                        <td>${user.email}</td>
                        <td>
                            <form name="assign-driver" action="${pageContext.request.contextPath}/route/assign/user" method="post">
                                <input name="assignmentId" type="hidden" value="${assignmentId}">
                                <input name="userId" type="hidden" value="${user.id}">
                                <button class="btn btn-primary"><spring:message code="button.assign" /></button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
