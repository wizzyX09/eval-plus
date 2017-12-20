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

    <title>Survey report</title>

    <!-- DataTables CSS -->
    <link href="../vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="../vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">

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
                <h1 class="page-header">Survey report</h1>
            </div>
            <!-- /.col-lg-12 -->


            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Reports on MCQ questions
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>Question</th>
                                    <th>Completely Agree</th>
                                    <th>Agree</th>
                                    <th>Neutral</th>
                                    <th>Disagree</th>
                                    <th>Completely Disagree</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="entry" items="${reports}">
                                    <c:choose>
                                        <c:when test="${entry.value.questionType == 'MCQ'}">
                                            <tr>
                                                <td>${entry.value.question}</td>
                                                <td>${entry.value.completelyAgree}</td>
                                                <td>${entry.value.agree}</td>
                                                <td>${entry.value.neutral}</td>
                                                <td>${entry.value.disagree}</td>
                                                <td>${entry.value.completelyDisagree}</td>
                                            </tr>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>

                                </tbody>
                            </table>
                        </div>
                        <!-- /.table-responsive -->
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-6 -->


            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Reports for opened questions
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>Question</th>
                                    <th>Answers</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="entry" items="${reports}">
                                    <c:choose>
                                        <c:when test="${entry.value.questionType == 'OPENED'}">
                                            <tr>
                                                <td>${entry.value.question}</td>
                                            </tr>
                                            <c:forEach var="answer" items="${entry.value.openedAnswers}" varStatus="i">
                                                <tr>
                                                    <td>${i.index}</td>
                                                    <td>${answer}</td>
                                                </tr>
                                            </c:forEach>

                                        </c:when>
                                    </c:choose>
                                </c:forEach>

                                </tbody>
                            </table>
                        </div>
                        <!-- /.table-responsive -->
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-6 -->


        </div>


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

<!-- DataTables JavaScript -->
<script src="../resources/vendor/datatables/js/jquery.dataTables.min.js"></script>
<script src="../resources/vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
<script src="../resources/vendor/datatables-responsive/dataTables.responsive.js"></script>

<!-- Custom Theme JavaScript -->
<script src="../resources/dist/js/sb-admin-2.js"></script>

<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>
    $(document).ready(function () {
        $('#dataTables-example').DataTable({
            responsive: true
        });
    });
</script>

</body>

</html>


