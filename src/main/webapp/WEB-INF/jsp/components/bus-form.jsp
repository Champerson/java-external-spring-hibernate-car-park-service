<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="carpark" tagdir="/WEB-INF/tags" %>

<div class="mx-auto" style="width: 300px">
    <spring:hasBindErrors name="busForm"><c:set var="validated" value="true"/></spring:hasBindErrors>
    <form:form name="bus" action="${param.link}" method="post" modelAttribute="busForm">
        <form:input path="id" type="hidden" value="${busForm.id}"/>
        <carpark:input name="number" value="${busForm.number}" validated="${validated}" mandatory="true" message="label.bus.number" generalError="true"/>
        <carpark:input name="model" value="${busForm.model}" validated="${validated}" message="label.bus.model"/>
        <carpark:input name="passengersCapacity" value="${busForm.passengersCapacity}" validated="${validated}" mandatory="true" message="label.bus.capacity"/>
        <carpark:input name="mileage" value="${busForm.mileage}" validated="${validated}" mandatory="true" message="label.bus.mileage"/>
        <carpark:input name="colourEn" value="${busForm.colourEn}" validated="${validated}" message="label.bus.colour.en"/>
        <carpark:input name="colourUa" value="${busForm.colourUa}" validated="${validated}" message="label.bus.colour.ua"/>
        <carpark:input name="notesEn" value="${busForm.notesEn}" validated="${validated}" message="label.bus.notes.en"/>
        <carpark:input name="notesUa" value="${busForm.notesUa}" validated="${validated}" message="label.bus.notes.ua"/>
        <button class="btn btn-primary" style="width:100%"><spring:message code="button.save" /></button>
    </form:form>
</div>

