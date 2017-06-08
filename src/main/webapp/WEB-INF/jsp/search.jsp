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
                <h3>${search}</h3><br>
                <div class="row" id="products">
                    <c:forEach items="${products}" var="product">
                        <div class="col-sm-4">
                            <c:if test="${not empty product.photos}">
                                <img src="${product.photos[0].href}" class="img-responsive" style="width:100%" alt="Image">
                            </c:if>
                            <c:if test="${empty product.photos}">
                                <img src="https://placehold.it/255x135?text=No image" class="img-responsive" style="width:100%" alt="Image">
                            </c:if>
                            <a href="/product?entity=product&command=show&id=${product.id}"><p>${product.name}</p></a>
                        </div>
                    </c:forEach>
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
<script src="resources/js/search-view.js"></script>
