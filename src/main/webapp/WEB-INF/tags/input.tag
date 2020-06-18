<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ attribute name="name" required="true" type="java.lang.String" description="Name of the input." %>
<%@ attribute name="value" required="false" type="java.lang.String" description="Value for the input." %>
<%@ attribute name="validated" required="false" type="java.lang.Boolean" description="Flag if field was validated previously." %>
<%@ attribute name="message" required="false" type="java.lang.String" description="Label text for the input." %>
<%@ attribute name="mandatory" required="false" type="java.lang.Boolean" description="Flag if field is mandatory." %>
<%@ attribute name="generalError" required="false" type="java.lang.Boolean" description="Flag if general error is present." %>

<c:set var="error">
    <form:errors path="${name}" />
    <c:if test="${generalError}"><form:errors/></c:if>
</c:set>

<div class="form-group">
    <label for="input-${name}">
        <spring:message code="${message}"/>
        <c:if test="${mandatory}"><font color="red"> *</font></c:if>
    </label>
    <c:choose>
        <c:when test="${not validated}">
            <form:input type="edit" path="${name}" value="${value}" class="form-control" id="input-${name}"/>
        </c:when>
        <c:when test="${not empty error}">
            <form:input type="edit" path="${name}" value="${value}" class="form-control is-invalid" id="input-${name}"/>
            <div class="invalid-feedback">
                ${error}
            </div>
        </c:when>
        <c:otherwise>
            <form:input type="edit" path="${name}" value="${value}" class="form-control is-valid" id="input-${name}"/>
        </c:otherwise>
    </c:choose>
</div>
