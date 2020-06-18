<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="carpark" tagdir="/WEB-INF/tags" %>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

        <title><spring:message code="title.registration.page" /></title>
    </head>
    <body>
        <jsp:include page="components/navigation-top-form.jsp"/>
        <center><h4><spring:message code="header.registration.page" /></h4></center></br>
        <div class="mx-auto" style="width: 300px">
            <spring:hasBindErrors name="userForm"><c:set var="validated" value="true"/></spring:hasBindErrors>
            <form:form name="registration" action="${pageContext.request.contextPath}/user/create" method="post" modelAttribute="userForm">
                <carpark:input name="login" value="${userForm.login}" validated="${validated}" mandatory="true" message="label.user.login" generalError="true"/>
                <carpark:input name="password" value="${userForm.password}" validated="${validated}" mandatory="true" message="label.user.password"/>
                <carpark:input name="name" value="${userForm.name}" validated="${validated}" message="label.user.name"/>
                <carpark:input name="email" value="${userForm.email}" validated="${validated}" mandatory="true" message="label.user.email"/>
                <carpark:input name="phone" value="${userForm.phone}" validated="${validated}" mandatory="true" message="label.user.phone"/>
                <carpark:input name="age" value="${userForm.age}" validated="${validated}" mandatory="true" message="label.user.age"/>
                <button class="btn btn-primary"><spring:message code="button.register" /></button>
            </form:form>
        </div>
    </body>
</html>