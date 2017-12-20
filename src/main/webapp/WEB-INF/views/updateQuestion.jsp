<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Eval Plus Home page</title>

    <!-- Bootstrap Core CSS -->
    <link href="../resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="../resources/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../resources/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="../resources/vendor/morrisjs/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="../resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
    <![endif]-->

</head>

<body>

<div id="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <!-- Include menu here-->
        <jsp:include page="menu.jsp"/>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Update question</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>


            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Make required changes and submit
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <form:form method="POST" modelAttribute="question"
                                               action="${contextPath}/updateQuestion">
                                        <h2 class="form-heading">Update a question</h2>
                                        <form:hidden path="id"></form:hidden>
                                        <div class="form-group ${error != null ? 'has-error' : ''}">
                                            <span>${message}</span>
                                            <form:input path="question" name="question" required="true" type="text"
                                                        class="form-control" placeholder="Question"
                                                        autofocus="true"/>
                                            <form:select path="type" name="questionType">
                                                <c:choose>
                                                    <c:when test="${question.type=='MCQ'}">
                                                        <option value="MCQ">Multiple Choice</option>
                                                        <option value="OPENED">Opened</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="OPENED">Opened</option>
                                                        <option value="MCQ">Multiple Choice</option>
                                                    </c:otherwise>
                                                </c:choose>

                                            </form:select>


                                            <div class="form-group ${error != null ? 'has-error' : ''}">
                                                <label>Status</label>
                                                <label class="radio-inline">
                                                    <form:radiobutton path="enabled" name="status"
                                                                      id="optionsRadiosInline1"
                                                                      value="1"/>Enabled
                                                </label>
                                                <label class="radio-inline">
                                                    <form:radiobutton path="enabled" name="status"
                                                                      id="optionsRadiosInline1"
                                                                      value="0"/>Disabled
                                                </label>
                                            </div>

                                            <div class="form-group ${error != null ? 'has-error' : ''}">
                                                <label>Required?</label>
                                                <label class="radio-inline">
                                                    <form:radiobutton path="required" name="required"
                                                                      id="optionsRadiosInline1"
                                                                      value="1"/>Required
                                                </label>
                                                <label class="radio-inline">
                                                    <form:radiobutton path="required" name="required"
                                                                      id="optionsRadiosInline1"
                                                                      value="0"/>Not required
                                                </label>
                                            </div>


                                            <span>${error}</span>
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                                            <button class="btn btn-lg btn-primary btn-block" type="submit">Update
                                            </button>
                                        </div>

                                    </form:form>
                                </div>

                            </div>
                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->


            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->


</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="../resources/vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="../resources/vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="../resources/vendor/metisMenu/metisMenu.min.js"></script>

<!-- Morris Charts JavaScript -->
<script src="../resources/vendor/raphael/raphael.min.js"></script>
<script src="../resources/vendor/morrisjs/morris.min.js"></script>
<script src="../resources/data/morris-data.js"></script>

<!-- Custom Theme JavaScript -->
<script src="../resources/dist/js/sb-admin-2.js"></script>
</body>

</html>









