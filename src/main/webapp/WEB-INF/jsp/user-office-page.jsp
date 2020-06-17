<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

        <title><spring:message code="title.user.office.page" /></title>
    </head>
    <body>
        <jsp:include page="components/navigation-top-form.jsp"/>
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success" role="alert">
                <spring:message code="${successMessage}" />
            </div>
        </c:if>

        <center><h4><spring:message code="header.user.office" /></h4></center></br>
        <center><table>
            <tr style="vertical-align: top">
                <td>
                    <c:choose>
                        <c:when test="${not empty user.assignment}">
                            <table class="table table-bordered">
                                <tr>
                                    <c:choose>
                                        <c:when test="${user.assignment.acceptedByDriver eq true}">
                                            <td><spring:message code="label.user.assignment.confirmed" /></td>
                                            <td>
                                                <form name="decline-driver-assignment" action="${pageContext.request.contextPath}/user/assignment/decline" method="post">
                                                    <button class="btn btn-secondary"><spring:message code="button.decline" /></button>
                                                </form>
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td style="vertical-align: middle"><spring:message code="label.user.assignment.not.confirmed" /></td>
                                            <td>
                                                <table>
                                                    <tr>
                                                        <td style="border:none">
                                                            <form name="confirm-assignment" action="${pageContext.request.contextPath}/user/assignment/accept" method="post">
                                                                <button class="btn btn-success"><spring:message code="button.accept" /></button>
                                                            </form>
                                                        </td>
                                                        <td style="border:none">
                                                            <form name="decline-driver-assignment" action="${pageContext.request.contextPath}/user/assignment/decline" method="post">
                                                                <button class="btn btn-danger"><spring:message code="button.decline" /></button>
                                                            </form>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                                <tr>
                                    <td><spring:message code="label.user.assigned.bus" /></td>
                                    <td>${user.assignment.bus.number}</td>
                                </tr>
                                <tr>
                                    <td><spring:message code="label.user.assigned.route" /></td>
                                    <td>${user.assignment.route.number}</td>
                                </tr>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <div class="card border-warning" style="width: 300px">
                                <div class="card-body text-warning">
                                    <p class="card-text"><spring:message code="label.user.no.assignment" /></p>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td style="width:30px"></td>
                <td>
                    <div class="card">
                        <div class="card-body">
                            <form:form class="mx-auto" style="width: 300px" name="edit-user" action="${pageContext.request.contextPath}/user/edit" method="post" modelAttribute="userForm">
                                <form:input path="id" type="hidden" value="${userForm.id}"/>
                                <div class="form-group">
                                    <label for="input-login"><spring:message code="label.user.login" /></label>
                                    <input id="input-login" class="form-control" type="text" disabled="true" value="${user.login}"/>
                                </div>
                                <div class="form-group">
                                    <label for="input-creationTime"><spring:message code="label.user.creation.time" /></label>
                                    <fmt:parseDate value="${user.creationTime}" type="both" pattern="yyyy-MM-dd'T'HH:mm" var="userCreationTime"/>
                                    <fmt:formatDate value="${userCreationTime}" type="both" dateStyle="short" timeStyle="short" var="parsedCreationTime"/>
                                    <input id="input-creationTime" class="form-control" type="text" disabled="true" value="${parsedCreationTime}"/>
                                </div>
                                <div class="form-group">
                                    <label for="input-name"><spring:message code="label.user.name" /></label>
                                    <form:input type="edit" path="name" value="${userForm.name}" class="form-control" id="input-name"/>
                                    <form:errors path="name" />
                                </div>
                                <div class="form-group">
                                    <label for="input-email"><spring:message code="label.user.email" /><font color="red"> *</font></label>
                                    <form:input type="edit" path="email" value="${userForm.email}" class="form-control" id="input-email"/>
                                    <form:errors path="email" />
                                </div>
                                <div class="form-group">
                                    <label for="input-phone"><spring:message code="label.user.phone" /><font color="red"> *</font></label>
                                    <form:input type="edit" path="phone" value="${userForm.phone}" class="form-control" id="input-phone"/>
                                    <form:errors path="phone" />
                                </div>
                                <div class="form-group">
                                    <label for="input-age"><spring:message code="label.user.age" /><font color="red"> *</font></label>
                                    <form:input type="edit" path="age" value="${userForm.age}" class="form-control" id="input-age"/>
                                    <form:errors path="age" />
                                </div>
                                <button class="btn btn-primary" style="width:100%"><spring:message code="button.save" /></button>
                            </form:form>
                        </div>
                    </div>
                </td>
                <td style="width:30px"></td>
                <td>
                </td>
            </tr>
        </table></center>
    </body>
</html>
