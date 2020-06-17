<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

        <title><spring:message code="title.index.page" /></title>
    </head>
    <body>
        <jsp:include page="components/navigation-top-form.jsp"/>
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success" role="alert">
                <spring:message code="${successMessage}" />
            </div>
        </c:if>
        <c:if test="${not empty param.credentialsError}">
            <div class="alert alert-danger" role="alert">
                <spring:message code="error.credentials.invalid"/>
            </div>
        </c:if>

        <center><h4><spring:message code="header.index.page" /></h4></center></br>
        <div class="mx-auto" style="width: 300px">
            <form name="authorization" action="${pageContext.request.contextPath}/login" method="post">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputLoginLabel"><spring:message code="label.user.login" /></span>
                    </div>
                    <input type="edit" class="form-control" id="inputLogin" aria-describedby="inputLoginLabel" name="username">
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputPasswordLabel"><spring:message code="label.user.password" /></span>
                    </div>
                    <input type="password" class="form-control" id="inputPassword" aria-describedby="inputPasswordLabel" name="password">
                </div>
                <button style="width:100%" class="btn btn-primary"><spring:message code="button.authorization" /></button>
            </form>
            <br>
            <a href="${pageContext.request.contextPath}/user/registration" class="btn btn-outline-primary" style="width:100%">
                <spring:message code="button.open.registration" />
            </a>
        </div>
    </body>
</html>