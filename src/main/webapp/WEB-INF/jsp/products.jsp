<%@include file="defines/Settings.jsp"%>
<%@include file="defines/ProductDefines.jsp"%>

<%@include file="templates/head.jsp" %>

<body>
    <div class="wrapper">
        <%@include file="templates/navbar/begin-navbar.jsp" %>
            <%@include file="templates/navbar/role-selector.jsp" %>
        <%@include file="templates/navbar/end-navbar.jsp" %>

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
                <div class="text-center">
                    <h3>${navProducts}</h3><br>
                    <div class="row" id="products">
                        #PRODUCTS
                    </div>
                </div><br>
            </div>
        </div>

        <%@include file="templates/end-page/footer.jsp" %>

        <%@include file="templates/end-page/begin-hidden-connector.jsp" %>
            <%@include file="templates/end-page/hidden-elements/signing.jsp" %>
            <%@include file="templates/end-page/hidden-elements/action-block.jsp" %>
            <%@include file="templates/end-page/hidden-elements/spinner-block.jsp" %>
        <%@include file="templates/end-page/end-hidden-connector.jsp" %>

        <link rel="stylesheet" href="resources/css/products.css">
        <script src="resources/js/spin.js"></script>
        <script src="resources/js/spinner-handler.js"></script>
        <script src="resources/js/json-handler.js"></script>
        <script src="resources/js/account-handler.js"></script>
    </div>

    <%@include file="templates/end-page.jsp" %>
<script src="resources/js/products.js"></script>
