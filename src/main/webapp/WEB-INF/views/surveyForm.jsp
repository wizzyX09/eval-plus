<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="header.jsp"/>

<div class="container">

    <c:choose>
        <c:when test="${surveyForm['new']}">
            <h1>Create</h1>
        </c:when>
        <c:otherwise>
            <h1>Update</h1>
        </c:otherwise>
    </c:choose>
    <br/>

    <spring:url value="/newSurvey" var="onSaveSurveyUrl"/>

    <form:form class="form-horizontal" method="post" action="${onSaveSurveyUrl}">

        End Date:<input type="date" name="endDate"/><br>
        Resubmission Allowed:<input type="checkbox" name="resubmissionAllowed"><br>
        Status:<input type="radio" name="status" value="OPENED">Opened <input type="radio" name="status" value="CLOSED">Closedd<br>
        Class :<select name="classOffered">
        <c:forEach var="lecture" items="${classList}">
            <option value="${lecture.id}">${lecture.name}</option>
        </c:forEach>

        </select>


        <br>
        <h2>Questions</h2><br>
        <c:forEach var="quest" items="${questionList}">
            <input type="checkbox" name="question" value="${quest.id}"/>${quest.question}<br>
        </c:forEach>


        <div class="form-group">
            <button type="submit" class="btn-lg btn-primary pull-right">Add</button>

        </div>
    </form:form>

</div>

<jsp:include page="footer.jsp"/>

</body>
</html>