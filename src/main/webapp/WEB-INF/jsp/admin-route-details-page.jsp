<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success" role="alert">
                <spring:message code="${successMessage}" />
            </div>
        </c:if>

        <center><h4><spring:message code="header.admin.route" /></h4></center></br>
        <center><table>
            <tr>
                <td>
                    <div class="card">
                        <div class="card-body">
                            <div class="mx-auto" style="width: 300px">
                                <div class="form-group">
                                    <label for="input-creationTime"><spring:message code="label.route.creation.time" /></label>
                                    <fmt:parseDate value="${route.creationTime}" type="both" pattern="yyyy-MM-dd'T'HH:mm" var="routeCreationTime"/>
                                    <fmt:formatDate value="${routeCreationTime}" type="both" dateStyle="short" timeStyle="short" var="parsedCreationTime"/>
                                    <input id="input-creationTime" class="form-control" type="text" disabled="true" value="${parsedCreationTime}"/>
                                </div>
                            </div>
                            <jsp:include page="components/route-form.jsp" >
                                <jsp:param name="link" value="${pageContext.request.contextPath}/route/edit" />
                            </jsp:include>
                        </div>
                    </div>
                </td>
                <td style="width:30px"></td>
                <td>
                    <c:if test="${empty route.assignments}">
                        <center><h5><spring:message code="label.route.no.assigned.buses" /></h5></center>
                    </c:if>
                    <c:if test="${not empty route.assignments}">
                        <center><h5><spring:message code="label.route.assigned.buses" /></h5></center>
                        <table class="table mx-auto table-bordered" style="width: 800px">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col"><spring:message code="label.bus.number" /></th>
                                    <th scope="col"><spring:message code="label.bus.assigned.driver" /></th>
                                    <th scope="col"><spring:message code="label.route.assignment.decline" /></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${route.assignments}" var="assignment" varStatus="loop">
                                    <tr>
                                        <th scope="row" style="vertical-align: middle">${loop.count}</th>
                                        <td style="vertical-align: middle">${assignment.bus.number}</td>
                                        <td style="vertical-align: middle">
                                            <c:choose>
                                                <c:when test="${empty assignment.driver}">
                                                    <form name="route-assign-driver" action="${pageContext.request.contextPath}/user/drivers/available" method="get">
                                                        <input type="hidden" name="assignmentId" value="${assignment.id}">
                                                        <button class="btn btn-success"><spring:message code="button.route.assign.driver" /></button>
                                                    </form>
                                                </c:when>
                                                <c:otherwise>
                                                    <table>
                                                        <tr>
                                                            <td style="border:none">
                                                                ${assignment.driver.name}</br>
                                                                <c:choose>
                                                                    <c:when test="${assignment.acceptedByDriver eq true}">
                                                                        <font color="green"><spring:message code="label.route.assignment.confirmed.by.user" /></font>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <font color="red"><spring:message code="label.route.assignment.waiting.confirmation" /></font>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td style="border:none;vertical-align: middle">
                                                                <form name="decline-driver-assignment" action="${pageContext.request.contextPath}/route/assignment/decline" method="post">
                                                                    <input type="hidden" name="assignmentId" value="${assignment.id}">
                                                                    <button class="btn btn-secondary"><spring:message code="button.decline" /></button>
                                                                </form>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td style="vertical-align: middle">
                                            <form name="delete-bus-assignment" action="${pageContext.request.contextPath}/route/assignment/delete" method="post">
                                                <input type="hidden" name="assignmentId" value="${assignment.id}">
                                                <button class="btn btn-secondary"><spring:message code="button.decline.bus" /></button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <div class="mx-auto" style="width: 300px">
                        <form name="add-bus" action="${pageContext.request.contextPath}/bus/buses/available" method="get">
                            <input name="routeId" type="hidden" value="${route.id}">
                            <button class="btn btn-success" style="width: 300px"><spring:message code="button.route.add.bus" /></button>
                        </form>
                    </div>
                </td>
            </tr>
        </table></center>
    </body>
</html>
