<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
        <jsp:include page="components/navigation-top-form.jsp"/>
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success" role="alert">
                <spring:message code="${successMessage}" />
            </div>
        </c:if>

        <center><h4><spring:message code="header.admin.user" /></h4></center></br>
        <table class="table table-bordered mx-auto"  style="width: 600px">
            <tr>
                <td><spring:message code="label.user.login" /></td>
                <td>${user.login}</td>
            </tr>
            <tr>
                <td><spring:message code="label.user.name" /></td>
                <td>${user.name}</td>
            </tr>
            <tr>
                <td><spring:message code="label.user.email" /></td>
                <td>${user.email}</td>
            </tr>
            <tr>
                <td><spring:message code="label.user.phone" /></td>
                <td>${user.phone}</td>
            </tr>
            <tr>
                <td><spring:message code="label.user.age" /></td>
                <td>${user.age}</td>
            </tr>
            <tr>
                <td><spring:message code="label.user.creation.time" /></td>
                <td>
                    <fmt:parseDate value="${user.creationTime}" type="both" pattern="yyyy-MM-dd'T'HH:mm" var="userCreationTime"/>
                    <fmt:formatDate value="${userCreationTime}" type="both" dateStyle="short" timeStyle="short" var="parsedCreationTime"/>
                    ${parsedCreationTime}
                </td>
            </tr>
            <tr>
                <td style="vertical-align: middle"><spring:message code="label.user.role" /></td>
                <td>
                    <form name="edit-user-role" action="${pageContext.request.contextPath}/user/role/edit" method="post">
                        <input type="hidden" name="userId" value="${user.id}">
                        <table>
                            <tr>
                                <td style="border:none">
                                    <select class="form-control" name="accessRole" size="1">
                                        <option value="ROLE_DRIVER" <c:if test="${user.accessRole == 'ROLE_DRIVER'}">selected="selected"</c:if>>
                                            <spring:message code="label.user.role.driver" />
                                        </option>
                                        <option value="ROLE_ADMIN" <c:if test="${user.accessRole == 'ROLE_ADMIN'}">selected="selected"</c:if>>
                                            <spring:message code="label.user.role.admin" />
                                        </option>
                                    </select>
                                </td>
                                <td style="border:none">
                                    <button class="btn btn-success"><spring:message code="button.save.changes" /></button>
                                </td>
                            </tr>
                        </table>
                    </form>
                </td>
            </tr>
            <c:if test="${not empty user.assignment}">
                <tr class="table-secondary">
                    <td style="vertical-align: middle"><spring:message code="label.user.assignment" /></td>
                    <td style="vertical-align: middle">
                        <table>
                            <tr>
                                <td style="border:none;vertical-align: middle">
                                    <c:choose>
                                        <c:when test="${user.assignment.acceptedByDriver eq true}">
                                            <font color="green"><spring:message code="label.route.assignment.confirmed.by.user" /></font>
                                        </c:when>
                                        <c:otherwise>
                                            <font color="red"><spring:message code="label.route.assignment.waiting.confirmation" /></font>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td style="border:none">
                                    <form name="decline-driver-assignment" action="${pageContext.request.contextPath}/user/assignment/admin/decline" method="post">
                                        <input type="hidden" name="userId" value="${user.id}">
                                        <button class="btn btn-secondary"><spring:message code="button.decline" /></button>
                                    </form>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr class="table-secondary">
                    <td><spring:message code="label.user.assigned.route" /></td>
                    <td>${user.assignment.route.number}</td>
                </tr>
                <tr class="table-secondary">
                    <td><spring:message code="label.user.assigned.bus" /></td>
                    <td>${user.assignment.bus.number}</td>
                </tr>
            </c:if>
        </table>
    </body>
</html>
