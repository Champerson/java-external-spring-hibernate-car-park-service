<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="carpark" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="mx-auto" style="width: 300px">
    <form:form name="route" action="${param.link}" method="post" modelAttribute="routeForm">
        <form:input path="id" type="hidden" value="${routeForm.id}"/>
        <div class="form-group">
            <label for="input-number"><spring:message code="label.route.number" /><font color="red"> *</font></label>
            <form:input type="edit" path="number" value="${routeForm.number}" class="form-control" id="input-number" />
            <form:errors path="number" />
        </div>
        <div class="form-group">
            <label for="input-length"><spring:message code="label.route.length" /><font color="red"> *</font></label>
            <form:input type="edit" path="length" value="${routeForm.length}" class="form-control" id="input-length" />
            <c:set var="lengthError"><form:errors path="length"/></c:set>
            <c:choose>
                <c:when test="${fn:containsIgnoreCase(lengthError, 'NumberFormatException')}">
                    <spring:message code="validation.route.length.invalid"/>
                </c:when>
                <c:otherwise>
                    ${lengthError}
                </c:otherwise>
            </c:choose>
        </div>
        <div class="form-group">
            <label for="input-descriptionEn"><spring:message code="label.route.description.en" /><font color="red"> *</font></label>
            <form:input type="text" path="descriptionEn" value="${routeForm.descriptionEn}" class="form-control" id="input-descriptionEn" />
            <form:errors path="descriptionEn" />
        </div>
        <div class="form-group">
            <label for="input-descriptionUa"><spring:message code="label.route.description.ua" /><font color="red"> *</font></label>
            <form:input type="text" path="descriptionUa" value="${routeForm.descriptionUa}" class="form-control" id="input-descriptionUa" />
            <form:errors path="descriptionUa" />
        </div>
        <button class="btn btn-primary" style="width:100%"><spring:message code="button.save" /></button>
    </form:form>
</div>