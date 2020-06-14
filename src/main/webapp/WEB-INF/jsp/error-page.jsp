<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

        <title><spring:message code="title.error.page" /></title>
    </head>
    <body>
        <div class="card mx-auto" style="width: 18rem">
            <div class="card-header text-white bg-danger mb-3">
                <spring:message code="header.error.page" />
            </div>
            <div class="card-body">
              <p class="card-text"><spring:message code="${errorMessage}" /></p>
            </div>
        </div>
    </body>
</html>
