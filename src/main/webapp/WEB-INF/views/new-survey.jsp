<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Create a new survey</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container">
    <form:form method="POST" action="${contextPath}/newSurvey" modelAttribute="surveyForm" class="form-signin">
       Please fill the form for the new Survey<br/>
        <fieldset>
            <legend>Primary detail</legend>
        <spring:bind path="endDate">
            <div class="${status.error ? 'has-error' : ''}">
                Open until:

                <form:input type="date" path="endDate" autofocus="true"></form:input>
                <form:errors path="endDate"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="status">
            <div class="${status.error ? 'has-error' : ''}">
                Status:

                <form:radiobutton  path="status"  value="OPENED"/>Opened
                <form:radiobutton  path="status"  value="CLOSED"/>Closed
                <form:errors path="status"></form:errors>
            </div>
        </spring:bind>
        </fieldset>

       <spring:bind path="resubmissionAllowed">
            <div class="${status.error ? 'has-error' : ''}">
                Resubmission Allowed:

                <form:radiobutton  path="resubmissionAllowed"  value="False"/>No
                <form:radiobutton  path="resubmissionAllowed"  value="True" />Yes
                <form:errors path="resubmissionAllowed"></form:errors>
            </div>
        </spring:bind>
        </fieldset>
        <fieldset>
            <legend>Choose questions</legend>
        <form:checkboxes items="${questions}" path="questions" itemLabel="question" itemValue="id" delimiter="<br"/>
        </fieldset>
        <fieldset>
            <form:select multiple="false" path="classOffered" items="${lectures}" itemLabel="name" itemValue="id"/>
        </fieldset>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
