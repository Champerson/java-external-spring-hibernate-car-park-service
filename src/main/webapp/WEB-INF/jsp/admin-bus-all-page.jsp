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

        <title><spring:message code="title.admin.buses.page" /></title>
    </head>
    <body>
        <jsp:include page="components/navigation-top-form.jsp"/>
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success" role="alert">
                <spring:message code="${successMessage}" />
            </div>
        </c:if>

        <center><h4><spring:message code="header.admin.all.buses.page" /></h4></center></br>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col"><spring:message code="label.bus.number" /></th>
                    <th scope="col"><spring:message code="label.bus.capacity" /></th>
                    <th scope="col"><spring:message code="label.bus.notes" /></th>
                    <th scope="col"><spring:message code="label.bus.assigned.route" /></th>
                    <th scope="col"><spring:message code="label.bus.assigned.driver" />"</th>
                    <th scope="col"><spring:message code="label.bus.open.details" /></th>
                    <th scope="col"><spring:message code="label.bus.delete" /></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${buses}" var="bus" varStatus="loop">
                    <tr>
                        <th scope="row">${loop.count}</th>
                        <td>${bus.number}</td>
                        <td>${bus.passengersCapacity}</td>
                        <td>${bus.notesEn}</td>
                        <td>${bus.assignment.route.number}</td>
                        <td>${bus.assignment.driver.name}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/bus/details/${bus.id}" class="btn btn-primary">
                                <spring:message code="button.details" />
                            </a>
                        </td>
                        <td>
                            <form name="delete-bus" action="${pageContext.request.contextPath}/bus/delete" method="post">
                                <input name="busId" type="hidden" value="${bus.id}">
                                <button class="btn btn-danger">X</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="mx-auto" style="width: 300px">
            <a href="${pageContext.request.contextPath}/bus/create" class="btn btn-success" style="width: 300px">
                <spring:message code="button.bus.create" />
            </a>
        </div>
    </body>
</html>
