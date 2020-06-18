<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="carpark" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="carpark" tagdir="/WEB-INF/tags" %>

<div class="mx-auto" style="width: 300px">
    <spring:hasBindErrors name="routeForm"><c:set var="validated" value="true"/></spring:hasBindErrors>
    <form:form name="route" action="${param.link}" method="post" modelAttribute="routeForm">
        <form:input path="id" type="hidden" value="${routeForm.id}"/>
        <carpark:input name="number" value="${routeForm.number}" validated="${validated}" mandatory="true" message="label.route.number" generalError="true"/>
        <carpark:input name="length" value="${routeForm.length}" validated="${validated}" mandatory="true" message="label.route.length"/>
        <carpark:input name="descriptionEn" value="${routeForm.descriptionEn}" validated="${validated}" mandatory="true" message="label.route.description.en"/>
        <carpark:input name="descriptionUa" value="${routeForm.descriptionUa}" validated="${validated}" mandatory="true" message="label.route.description.ua"/>
        <button class="btn btn-primary" style="width:100%"><spring:message code="button.save" /></button>
    </form:form>
</div>