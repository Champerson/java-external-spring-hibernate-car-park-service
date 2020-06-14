<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <ul class="navbar-nav">
        <li class="nav-item">
            <form name="localeEn" action="${param.link}" method="post">
                <input name="lang" type="hidden" value="en">
                <c:if test="${not empty param.pageToRedirect}">
                    <input name="pageToRedirect" type="hidden" value="${param.pageToRedirect}">
                </c:if>
                <c:if test="${not empty param.additionalParameterName}">
                    <input name="${param.additionalParameterName}" type="hidden" value="${param.additionalParameterValue}">
                </c:if>
                <c:if test="${not empty param.successMessage}">
                    <input name="successMessage" type="hidden" value="${param.successMessage}">
                </c:if>
                <button class="btn btn-outline-primary" type="submit"><spring:message code="button.locale.en" /></button>
            </form>
        </li>
        <li class="nav-item ml-2">
            <form name="localeUa" action="${param.link}" method="post">
                <input name="lang" type="hidden" value="uk">
                <c:if test="${not empty param.pageToRedirect}">
                    <input name="pageToRedirect" type="hidden" value="${param.pageToRedirect}">
                </c:if>
                <c:if test="${not empty param.additionalParameterName}">
                    <input name="${param.additionalParameterName}" type="hidden" value="${param.additionalParameterValue}">
                </c:if>
                <c:if test="${not empty param.successMessage}">
                    <input name="successMessage" type="hidden" value="${param.successMessage}">
                </c:if>
                <button class="btn btn-outline-primary" type="submit"><spring:message code="button.locale.ua" /></button>
            </form>
        </li>
        <c:if test="${not empty sessionScope.userId}">
            <li class="nav-item ml-2">
                <form name="logout" action="${pageContext.request.contextPath}/user/logout" method="post">
                    <button class="btn btn-outline-secondary bg-light" type="submit"><spring:message code="button.logout" /></button>
                </form>
            </li>
        </c:if>
        <c:if test="${not empty param.backButton}">
            <li class="nav-item ml-2">
                <form name="back" action="${pageContext.request.contextPath}/user/redirect" method="post">
                    <input name="pageToRedirect" type="hidden" value="admin-menu-page">
                    <button class="btn btn-outline-secondary bg-light" type="submit"><spring:message code="button.back" /></button>
                </form>
            </li>
        </c:if>
    </ul>
</nav>
</br>
