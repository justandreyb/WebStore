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

            <div class="container-fluid text-center">
                <div class="container-fluid">
                    <div class="col-sm-10">
                        <h3>${orderLabel}</h3>
                    </div>
                    <div class="col-sm-2">
                        <input type="button" class="btn btn-primary btn-add-to-order" value="Buy order" onclick="buyOrder()">
                    </div>
                </div>
                <hr>
                <br>
                <div class="row" id="things">
                    <c:forEach items="${order.products}" var="product">
                        <div class="row">
                            <div class="col-sm-4">
                                <c:if test="${not empty product.photos}">
                                    <img src="${product.photos[0].href}" class="img-responsive" style="width:100%" alt="Image">
                                </c:if>
                                <c:if test="${empty product.photos}">
                                    <img src="https://placehold.it/255x135?text=No image" class="img-responsive" style="width:100%" alt="Image">
                                </c:if>
                            </div>
                            <div class="col-sm-6 thing-desc-box">
                                <h3>${product.name}</h3>
                                <label>${category} : ${product.category}</label>
                                <br>
                                <label>${price} : ${product.price}</label>
                            </div>
                            <div class="col-sm-2">
                                <a href="/card?entity=account&command=delete_from_order&productId=${product.id}"><input type="button" class="btn btn-primary btn-add-to-order" value="Delete"></a>
                            </div>
                        </div>
                        <br>
                    </c:forEach>
                </div>
            </div><br>

        </div>
    </div>

    <%@include file="templates/end-page/begin-hidden-connector.jsp" %>
    <%@include file="templates/end-page/hidden-elements/signing.jsp" %>
    <%@include file="templates/end-page/hidden-elements/action-block.jsp" %>
    <%@include file="templates/end-page/hidden-elements/spinner-block.jsp" %>
    <%@include file="templates/end-page/end-hidden-connector.jsp" %>

    <%@include file="templates/end-page/footer.jsp" %>

    <link rel="stylesheet" href="resources/css/products.css">
    <script src="resources/js/spin.js"></script>
    <script src="resources/js/spinner-handler.js"></script>
    <script src="resources/js/json-handler.js"></script>
    <script src="resources/js/account-handler.js"></script>
</div>

<%@include file="templates/end-page.jsp" %>
<script src="resources/js/product.js"></script>
