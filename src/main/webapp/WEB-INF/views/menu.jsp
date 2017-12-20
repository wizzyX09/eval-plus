<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="index.html">Eval Plus</a>
    </div>
    <!-- /.navbar-header -->

    <ul class="nav navbar-top-links navbar-right">

        <!-- /.dropdown  profile-->
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-user">
                <li>
                    <a href="#"><i class="fa fa-user fa-fw"></i> <spring:message code="user.profile" text=""/></a>
                </li>
                <li><a href="#"><i class="fa fa-gear fa-fw"></i> <spring:message code="account.settings"
                                                                                 text="Settings"/></a>
                </li>
                <li class="divider"></li>
                <li><a href="../logout"><i class="fa fa-sign-out fa-fw"></i> <spring:message code="user.logout"
                                                                                             text="Logout"/></a>
                </li>
                <li class="divider"></li>
                <li><a href="?language=en"><i class="fa fa-language"></i> English</a>
                <li><a href="?language=fr"><i class="fa fa-language"></i> Francais</a>
                </li>
            </ul>

            <!-- /.dropdown-user -->
        </li>
        <!-- /.dropdown -->
    </ul>
    <!-- /.navbar-top-links -->


    <div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse">
            <ul class="nav" id="side-menu">
                <li>
                    <a href="../welcome"><i class="fa fa-dashboard fa-fw"></i><spring:message code="app.home"
                                                                                              text="Dashboard"/></a>
                </li>
                <!-- menu -->
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li>
                        <a href="#"><i class="fa fa-user" aria-hidden="true"></i><spring:message code="general.account"
                                                                                                 text="Account"/><span
                                class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="../generateAccount"><spring:message code="account.generate"
                                                                             text="Generate"/></a>
                            </li>
                            <li>
                                <a href="../manageAccount"><spring:message code="account.manage" text="Manage"/></a>
                            </li>
                        </ul>
                    </li>
                </sec:authorize>
                <!-- menu -->

                <li>
                    <a href="#"><i class="fa fa-commenting-o" aria-hidden="true"></i><spring:message code="menu.survey"
                                                                                                     text="Survey"/><span
                            class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <sec:authorize access="hasRole('ROLE_FACULTY')">
                            <li>
                                <a href="../newSurvey"><spring:message code="menu.survey.new" text=""/></a>
                            </li>
                            <li>
                                <a href="../manageSurvey"><spring:message code="menu.survey.manage" text=""/></a>
                            </li>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_STUDENT')">
                            <li>
                                <a href="../takeSurvey"><spring:message code="menu.survey.take" text=""/></a>
                            </li>
                        </sec:authorize>
                    </ul>
                </li>

                <!-- menu -->
                <sec:authorize access="hasRole('ROLE_FACULTY,ROLE_ADMIN')">
                    <li>
                        <a href="#"><i class="fa fa-question" aria-hidden="true"></i><spring:message
                                code="menu.question" text=""/><span
                                class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="../newQuestion"><spring:message code="menu.question.new" text=""/></a>
                            </li>
                            <li>
                                <a href="../questionList"><spring:message code="menu.question.manage" text=""/></a>
                            </li>
                        </ul>
                    </li>

                </sec:authorize>
            </ul>
        </div>
        <!-- /.sidebar-collapse -->
    </div>
    <!-- /.navbar-static-side -->
</nav>