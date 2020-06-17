<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="currentPageRegistration" value="${fn:containsIgnoreCase(pageContext.request.requestURI, 'registration')}"/>
<c:set var="currentPageAdminMenu" value="${fn:containsIgnoreCase(pageContext.request.requestURI, 'admin-menu')}"/>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <ul class="navbar-nav">
        <li class="nav-item">
            <a href="?lang=en" class="btn btn-outline-primary">
                <spring:message code="button.locale.en" />
            </a>
        </li>
        <li class="nav-item ml-2">
            <a href="?lang=uk" class="btn btn-outline-primary">
                <spring:message code="button.locale.ua" />
            </a>
        </li>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <c:if test="${not currentPageAdminMenu}">
                <li class="nav-item ml-2">
                    <a href="${pageContext.request.contextPath}/user/admin/office" class="btn btn-outline-secondary bg-light">
                        <spring:message code="button.back.admin" />
                    </a>
                </li>
            </c:if>
        </sec:authorize>
        <c:if test="${currentPageRegistration}">
            <li class="nav-item ml-2">
                <a href="${pageContext.request.contextPath}/login" class="btn btn-outline-secondary bg-light">
                    <spring:message code="button.back.login" />
                </a>
            </li>
        </c:if>
        <sec:authorize access="isAuthenticated()">
            <li class="nav-item ml-2">
                <a href="${pageContext.request.contextPath}/logout" class="btn btn-outline-secondary bg-light">
                    <spring:message code="button.logout" />
                </a>
            </li>
        </sec:authorize>
    </ul>
</nav>
</br>
