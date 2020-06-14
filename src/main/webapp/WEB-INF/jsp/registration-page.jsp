<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
        <center><h4><spring:message code="header.registration.page" /></h4></center></br>
        <div class="mx-auto" style="width: 300px">
            <form:form name="registration" action="${pageContext.request.contextPath}/user/create" method="post" modelAttribute="user">
                <div class="form-group">
                    <label for="input-login"><spring:message code="label.user.login" /><font color="red"> *</font></label>
                    <form:input type="edit" path="login" value="${user.login}" placeholder="John" class="form-control" id="input-login"/>
                    <form:errors path="login"/>
                </div>
                <div class="form-group">
                    <label for="input-password"><spring:message code="label.user.password" /><font color="red"> *</font></label>
                    <form:input type="edit" path="password" value="${user.password}" placeholder="John123" class="form-control" id="input-password"/>
                    <form:errors path="password"/>
                </div>
                <div class="form-group">
                    <label for="input-name"><spring:message code="label.user.name" /></label>
                    <form:input type="edit" path="name" value="${user.name}" placeholder="John Smith" class="form-control" id="input-name"/>
                    <form:errors path="name"/>
                </div>
                <div class="form-group">
                    <label for="input-email"><spring:message code="label.user.email" /><font color="red"> *</font></label>
                    <form:input type="edit" path="email" value="${user.email}" placeholder="john.smith@gmail.com" class="form-control" id="input-email"/>
                    <form:errors path="email"/>
                </div>
                <div class="form-group">
                    <label for="input-phone"><spring:message code="label.user.phone" /><font color="red"> *</font></label>
                    <form:input type="edit" path="phone" value="${user.phone}" placeholder="0672234590" class="form-control" id="input-phone"/>
                    <form:errors path="phone"/>
                </div>
                <div class="form-group">
                    <label for="input-age"><spring:message code="label.user.age" /><font color="red"> *</font></label>
                    <form:input type="edit" path="age" value="${user.age}" placeholder="35" class="form-control" id="input-age"/>
                    <form:errors path="age"/>
                </div>
                <button class="btn btn-primary" type="submit"><spring:message code="button.register" /></button>
            </form:form>
        </div>
    </body>
</html>