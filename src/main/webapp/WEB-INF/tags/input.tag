<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="name" required="true" type="java.lang.String" description="Name of the input." %>
<%@ attribute name="value" required="false" type="java.lang.String" description="Value for the input." %>
<%@ attribute name="validated" required="false" type="java.lang.Boolean" description="Flag if field was validated previously." %>
<%@ attribute name="error" required="false" type="java.lang.String" description="Error message key." %>
<%@ attribute name="placeholder" required="false" type="java.lang.String" description="Placeholder for the input." %>


<c:choose>
    <c:when test="${not validated}">
        <input type="text" name="${name}" class="form-control" id="input-${name}" value="${value}" placeholder="${placeholder}">
    </c:when>
    <c:when test="${not empty error}">
        <input type="text" name="${name}" class="form-control is-invalid" id="input-${name}" value="${value}">
        <div class="invalid-feedback">
            <spring:message code="${error}" />
        </div>
    </c:when>
    <c:otherwise>
        <input type="text" name="${name}" class="form-control is-valid" id="input-${name}" value="${value}">
    </c:otherwise>
</c:choose>