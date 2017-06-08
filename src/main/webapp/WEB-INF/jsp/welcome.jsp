<%@include file="defines/Settings.jsp"%>
<%@include file="defines/WelcomeDefines.jsp"%>

<%@include file="templates/head.jsp" %>
<body>
<div class="wrapper">
    <%@include file="templates/navbar/begin-navbar.jsp" %>
    <%@include file="templates/navbar/role-selector.jsp" %>
    <%@include file="templates/navbar/end-navbar.jsp" %>

    <br>
    <div class="inner">
        <div class="container col-sm-2 hidden-xs">
            <br>
            <div class="well">
                <ul id="navigation-box" class="nav nav-pills nav-stacked">
                    <li class="active"><span class="glyphicon glyphicon-tags"></span>   ${brands}</li>
                    #BRANDS
                    <li class="active"><span class="glyphicon glyphicon-ice-lolly-tasted"></span>   ${categories}</li>
                    #CATEGORIES
                </ul>
            </div>

            <br>
        </div>

        <div class="container col-sm-10">
            <div class="topProducts text-center">
                <h3>${navProducts}</h3><br>
                <div id="topProducts" class="carousel slide" data-ride="carousel">
                    <!-- Indicators -->
                    #NAV_ELEMS

                    <!-- Wrapper for slides -->
                    #SLIDES

                    <!-- Left and right controls -->
                    <div id="sliderButtons">
                        <a class="left carousel-control" href="#topProducts" role="button" data-slide="prev">
                            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        </a>
                        <a class="right carousel-control" href="#topProducts" role="button" data-slide="next">
                            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                        </a>
                    </div>
                </div>
            </div>

            <br>

            <div class="well text-center" id="search-box">
                <h4>${search}</h4>
                <p>Here will be search box</p>
            </div>
        </div>

        <%@include file="templates/end-page/begin-hidden-connector.jsp" %>
            <%@include file="templates/end-page/hidden-elements/signing.jsp" %>
            <%@include file="templates/end-page/hidden-elements/action-block.jsp" %>
            <%@include file="templates/end-page/hidden-elements/spinner-block.jsp" %>
        <%@include file="templates/end-page/end-hidden-connector.jsp" %>
    </div>
    <script src="resources/js/spin.js"></script>
    <script src="resources/js/spinner-handler.js"></script>
    <script src="resources/js/json-handler.js"></script>
    <script src="resources/js/account-handler.js"></script>
</div>

<%@include file="templates/end-page/footer.jsp" %>

<%@include file="templates/end-page.jsp" %>
<script src="resources/js/welcome.js"></script>
