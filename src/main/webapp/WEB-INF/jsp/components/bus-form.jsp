<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="mx-auto" style="width: 300px">
    <form:form name="bus" action="${param.link}" method="post" modelAttribute="busForm">
        <form:input path="id" type="hidden" value="${busForm.id}"/>
        <div class="form-group">
            <label for="input-number"><spring:message code="label.bus.number" /><font color="red"> *</font></label>
            <form:input type="edit" path="number" value="${busForm.number}" class="form-control" id="input-number" />
            <form:errors path="number" />
        </div>
        <div class="form-group">
            <label for="input-model"><spring:message code="label.bus.model" /></label>
            <form:input type="edit" path="model" value="${busForm.model}" class="form-control" id="input-model"/>
            <form:errors path="model" />
        </div>
        <div class="form-group">
            <label for="input-passengersCapacity"><spring:message code="label.bus.capacity" /><font color="red"> *</font></label>
            <form:input type="edit" path="passengersCapacity" value="${busForm.passengersCapacity}" class="form-control" id="input-passengersCapacity"/>
            <c:set var="passengersCapacityError"><form:errors path="passengersCapacity"/></c:set>
            <c:choose>
                <c:when test="${fn:containsIgnoreCase(passengersCapacityError, 'NumberFormatException')}">
                    <spring:message code="validation.bus.capacity.invalid"/>
                </c:when>
                <c:otherwise>
                    ${passengersCapacityError}
                </c:otherwise>
            </c:choose>
        </div>
        <div class="form-group">
            <label for="input-mileage"><spring:message code="label.bus.mileage" /><font color="red"> *</font></label>
            <form:input type="edit" path="mileage" value="${busForm.mileage}" class="form-control" id="input-mileage"/>
            <c:set var="mileageError"><form:errors path="mileage"/></c:set>
            <c:choose>
                <c:when test="${fn:containsIgnoreCase(mileageError, 'NumberFormatException')}">
                    <spring:message code="validation.bus.mileage.invalid"/>
                </c:when>
                <c:otherwise>
                    ${mileageError}
                </c:otherwise>
            </c:choose>
        </div>
        <div class="form-group">
            <label for="input-colourEn"><spring:message code="label.bus.colour.en" /></label>
            <form:input type="edit" path="colourEn" value="${busForm.colourEn}" class="form-control" id="input-colourEn"/>
            <form:errors path="colourEn" />
        </div>
        <div class="form-group">
            <label for="input-colourUa"><spring:message code="label.bus.colour.ua" /></label>
            <form:input type="edit" path="colourUa" value="${busForm.colourUa}" class="form-control" id="input-colourUa"/>
            <form:errors path="colourUa" />
        </div>
        <div class="form-group">
            <label for="input-notesEn"><spring:message code="label.bus.notes.en" /></label>
            <form:input type="text" path="notesEn" value="${busForm.notesEn}" class="form-control" id="input-notesEn"/>
            <form:errors path="notesEn" />
        </div>
        <div class="form-group">
            <label for="input-notesUa"><spring:message code="label.bus.notes.ua" /></label>
            <form:input type="text" path="notesUa" value="${busForm.notesUa}" class="form-control" id="input-notesUa"/>
            <form:errors path="notesUa" />
        </div>
        <button class="btn btn-primary" style="width:100%"><spring:message code="button.save" /></button>
    </form:form>
</div>

