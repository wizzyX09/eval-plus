<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: enkhbayar
  Date: 19/12/2017
  Time: 00:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit account</title>

</head>
<body>

<form:form commandName="userForm" action="${contextPath}/manageAccount" method="post">
    <title>Edit account</title>
    <form:hidden path="id"/>
    <div class="form-group  ${error != null ? 'has-error' : ''}">
        <span>${message}</span>
        <form:input cssClass="form-control" placeholder="Username" path="username"/>
    </div>
    <div class="form-group  ${error != null ? 'has-error' : ''}">
        <span>${message}</span>
        <form:password cssClass="form-control" placeholder="password" path="password"/>
    </div>
    <span>${error}</span>
    <security:csrfInput/>
    <!-- Change this to a button or input when using this as a form -->
    <button class="btn btn-lg btn-success btn-block" type="submit">Edit</button>
</form:form>

</body>
</html>
