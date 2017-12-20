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
    <![endif]-->

</head>

<body>

<div id="wrapper">

    <!-- Navigation -->
    <!-- Include menu here-->
    <jsp:include page="menu.jsp"/>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Dashboard</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>


        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Please fill answer those questions
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-6">

                                <spring:url value="../takeSurvey" var="onTakeSurveyUrl"/>
                                <form role="form" action="${onTakeSurveyUrl}" method="post">
                                    <input type="hidden" value="${survey.id}" name="surveyId">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                                    <c:forEach var="quest" items="${survey.questions}">
                                        <c:choose>
                                            <c:when test="${quest.type == 'MCQ'}">
                                                <label>${quest.question}</label>
                                                <div class="form-group">
                                                    <label class="radio-inline">
                                                        <input required="${question.required}" name="${quest.id}"
                                                               type="radio"
                                                               id="optionsRadiosInline1"
                                                               value="CompletelyAgree">Completely
                                                        Agree
                                                    </label>
                                                    <label class="radio-inline">
                                                        <input type="radio" name="${quest.id}"
                                                               id="optionsRadiosInline2" value="Agree">Agree
                                                    </label>
                                                    <label class="radio-inline">
                                                        <input type="radio" name="${quest.id}"
                                                               id="optionsRadiosInline3" value="Neutral">Neutral
                                                    </label>
                                                    <label class="radio-inline">
                                                        <input type="radio" name="${quest.id}"
                                                               id="optionsRadiosInline4" value="Disagree">Disagree
                                                    </label>
                                                    <label class="radio-inline">
                                                        <input type="radio" name="${quest.id}"
                                                               id="optionsRadiosInline5"
                                                               value="CompletelyDisagree">Completely
                                                        Disagree
                                                    </label>
                                                </div>

                                            </c:when>
                                            <c:otherwise>
                                                <div class="form-group">
                                                    <label>${quest.question}</label>
                                                    <textarea required="${question.required}" name="${quest.id}"
                                                              class="form-control"
                                                              rows="3"></textarea>
                                                </div>
                                            </c:otherwise>

                                        </c:choose>
                                    </c:forEach>


                                    <button type="submit" class="btn btn-default">Submit</button>

                                </form>
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


