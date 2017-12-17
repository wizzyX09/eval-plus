<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

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

    <title>Create an account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<spring:url value="/takeSurvey" var="onTakeSurveyUrl"/>
<div class="container">
    <form:form class="form-horizontal" method="post" action="${onTakeSurveyUrl}">

        <input type="hidden" value="${survey.id}" name="surveyId">

        <br>
        <h2>Questions</h2><br>
        <%--  <c:set var="MCQ" value="MCQ"/>--%>
        <c:forEach var="quest" items="${survey.questions}">
            <h3> ${quest.question}</h3><br>
            <c:choose>
                <c:when test="${quest.type == 'MCQ'}">
                    <input type="checkbox" name="quest${quest.id}" value="${quest.id},CompletelyAgree"/>Completely Agree
                    <input type="checkbox" name="quest${quest.id}" value="${quest.id},Agree"/>Agree
                    <input type="checkbox" name="quest${quest.id}" value="${quest.id},Neutral"/>Neutral
                    <input type="checkbox" name="quest${quest.id}" value="${quest.id},Disagree"/>Disagree
                    <input type="checkbox" name="quest${quest.id}"
                           value="${quest.id},CompletelyDisagree"/>Completely Disagree
                </c:when>


            </c:choose>
            <br>
        </c:forEach>


        <div class="form-group">
            <button type="submit" class="btn-lg btn-primary pull-right">Submit answers</button>

        </div>
    </form:form>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
