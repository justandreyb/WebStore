<%@include file="Settings.jsp" %>
<%@include file="defines/DashboardDefines.jsp"%>

<%@include file="templates/head.jsp" %>

<body>
    <%@include file="templates/navbar/begin-navbar.jsp" %>

        <c:if test="${sessionScope.user != null}">
        <%@include file="templates/navbar/accounts/userAccount.jspx" %>
        </c:if>

        <c:if test="${sessionScope.user == null}">
            <%@include file="templates/navbar/accounts/guestAccount.jspx" %>
        </c:if>

    <%@include file="templates/navbar/end-navbar.jsp" %>

    <h2>${about}</h2>

    <div class="container-fluid">
        <div class="row content">
            <div class="dashboard-header col-sm-2 hidden-xs">
                <h2>${webStore}</h2>

                <br>

                <ul class="nav nav-pills nav-stacked">
                    <li class="active"><a href="#main"><span class="glyphicon glyphicon-wrench"></span> ${dashboard}</a></li>
                    <li><a href="#profiles"><span class="glyphicon glyphicon-user"></span> ${profiles}</a></li>
                    <li><a href="#beans"><span class="glyphicon glyphicon-apple"></span> ${things}</a></li>
                    <li><a href="#products"><span class="glyphicon glyphicon-usd"></span> ${products}</a></li>
                </ul>

                <br>
            </div>

            <br>
            <div class="dashboard-inner col-sm-9">

                <div class="well text-center" id="main">
                    <h4 onclick="simpleAJAXRequest()">${dashboard}</h4>
                    <p>${dashboardInfo}</p>
                </div>

                <div id="profiles">
                    <h4><span class="glyphicon glyphicon-user"></span> ${profiles}</h4>
                    <p>${profilesInfo}</p>
                    <br>
                    <div class="well text-center">
                        <br>
                        <div class="row">

                            <div class="dashboard-button col-sm-4" onclick="getAccountChangingRoleForm()">
                                <a name="button" value="change-user-role">
                                    <div class="well">
                                        <h4><span class="glyphicon glyphicon-sunglasses"></span></h4>
                                        <p>${changeUserRole}</p>

                                    </div>
                                </a>
                            </div>

                            <div class="dashboard-button col-sm-4" onclick="getAccountBlockingForm()">
                                <a>
                                    <div class="well">
                                        <h4><span class="glyphicon glyphicon-ban-circle"></span></h4>
                                        <p>${blockUser}</p>

                                    </div>
                                </a>
                            </div>

                            <div class="dashboard-button col-sm-4" onclick="getAccountDeletingForm()">
                                <a>
                                    <div class="well">
                                        <h4><span class="glyphicon glyphicon-remove-circle"></span></h4>
                                        <p>${btnDelete}</p>

                                    </div>
                                </a>
                            </div>

                        </div>
                    </div>
                </div>
                <br>
                <div id="beans">
                    <h4><span class="glyphicon glyphicon-apple"></span> ${things}</h4>
                    <p>${thingsInfo}</p>
                    <br>

                    <div class="well text-center" id="categories">
                        <h4><span class="glyphicon glyphicon-th-list"></span> ${categories}</h4>
                        <p>${categoriesInfo}</p>
                        <br>

                        <div class="row">
                            <div class="dashboard-button col-sm-4">
                                <a name="button" value="create-category">
                                    <div class="well">
                                        <h4><span class="glyphicon glyphicon-plus"></span></h4>
                                        <p>${btnCreate}</p>

                                    </div>
                                </a>
                            </div>

                            <div class="dashboard-button col-sm-4">
                                <a name="button" value="update-category">
                                    <div class="well">
                                        <h4><span class="glyphicon glyphicon-refresh"></span></h4>
                                        <p>${btnUpdate}</p>

                                    </div>
                                </a>
                            </div>

                            <div class="dashboard-button col-sm-4">
                                <a name="button" value="delete-category">
                                    <div class="well">
                                        <h4><span class="glyphicon glyphicon-eye-close"></span></h4>
                                        <p>${btnDelete}</p>

                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="well text-center" id="brands">
                        <h4><span class="glyphicon glyphicon-registration-mark"></span> ${brands}</h4>
                        <p>${brandsInfo}</p>
                        <br>

                        <div class="row">
                            <div class="dashboard-button col-sm-4" onclick="getBrandAddingForm()">
                                <a name="button">
                                    <div class="well">
                                        <h4><span class="glyphicon glyphicon-plus"></span></h4>
                                        <p>${btnCreate}</p>

                                    </div>
                                </a>
                            </div>

                            <div class="dashboard-button col-sm-4" onclick="getBrandEditingForm()">
                                <a name="button">
                                    <div class="well">
                                        <h4><span class="glyphicon glyphicon-refresh"></span></h4>
                                        <p>${btnUpdate}</p>

                                    </div>
                                </a>
                            </div>

                            <div class="dashboard-button col-sm-4" onclick="getBrandDeletingForm()">
                                <a name="button">
                                    <div class="well">
                                        <h4><span class="glyphicon glyphicon-eye-close"></span></h4>
                                        <p>${btnDelete}</p>

                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="well text-center" id="things">
                        <h4><span class="glyphicon glyphicon-ice-lolly-tasted"></span> ${things}</h4>
                        <p>${thingsInfo}</p>
                        <br>

                        <div class="row">
                            <div class="dashboard-button col-sm-4">
                                <a name="button" value="create-thing">
                                    <div class="well">
                                        <h4><span class="glyphicon glyphicon-plus"></span></h4>
                                        <p>${btnCreate}</p>

                                    </div>
                                </a>
                            </div>

                            <div class="dashboard-button col-sm-4">
                                <a name="button" value="update-thing">
                                    <div class="well">
                                        <h4><span class="glyphicon glyphicon-refresh"></span></h4>
                                        <p>${btnUpdate}</p>

                                    </div>
                                </a>
                            </div>

                            <div class="dashboard-button col-sm-4">
                                <a name="button" value="delete-thing">
                                    <div class="well">
                                        <h4><span class="glyphicon glyphicon-eye-close"></span></h4>
                                        <p>${btnDelete}</p>

                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="well text-center" id="photos">
                        <h4><span class="glyphicon glyphicon-camera"></span> ${photos}</h4>
                        <p>${photosInfo}</p>
                        <br>

                        <div class="row">
                            <div class="dashboard-button col-sm-6">
                                <a name="button" value="add-photo">
                                    <div class="well">
                                        <h4><span class="glyphicon glyphicon-plus"></span></h4>
                                        <p>${btnAdd}</p>

                                    </div>
                                </a>
                            </div>

                            <div class="dashboard-button col-sm-6">
                                <a name="button" value="delete-photo">
                                    <div class="well">
                                        <h4><span class="glyphicon glyphicon-eye-close"></span></h4>
                                        <p>${btnDelete}</p>

                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="well text-center" id="reviews">
                        <h4><span class="glyphicon glyphicon-check"></span> ${reviews}</h4>
                        <p>${reviewsInfo}</p>
                        <br>

                        <div class="row">
                            <div class="dashboard-button col-sm-6">
                                <a name="button" value="add-review">
                                    <div class="well">
                                        <h4><span class="glyphicon glyphicon-pencil"></span></h4>
                                        <p>${btnAdd}</p>

                                    </div>
                                </a>
                            </div>

                            <div class="dashboard-button col-sm-6">
                                <a name="button" value="delete-review">
                                    <div class="well">
                                        <h4><span class="glyphicon glyphicon-apple"></span></h4>
                                        <p>${btnDelete}</p>

                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>

                </div>
                <br>
                <div id="products">
                    <h4><span class="glyphicon glyphicon-usd"></span> ${products}</h4>
                    <p>${productsInfo}</p>

                    <br>

                    <div class="well text-center" id="editing-products">
                        <h4><span class="glyphicon glyphicon-wrench"></span> ${products}</h4>
                        <p>${productsInfo}</p>
                        <br>
                        <div class="row">

                            <div class="dashboard-button col-sm-4">
                                <a name="button" value="create-product">
                                    <div class="well">
                                        <h4><span class="glyphicon glyphicon-plus"></span></h4>
                                        <p>${btnCreate}</p>

                                    </div>
                                </a>
                            </div>

                            <div class="dashboard-button col-sm-4">
                                <a name="button" value="update-product">
                                    <div class="well">
                                        <h4><span class="glyphicon glyphicon-refresh"></span></h4>
                                        <p>${btnUpdate}</p>

                                    </div>
                                </a>
                            </div>

                            <div class="dashboard-button col-sm-4">
                                <a name="button" value="delete-product">
                                    <div class="well">
                                        <h4><span class="glyphicon glyphicon-remove-circle"></span></h4>
                                        <p>${btnDelete}</p>

                                    </div>
                                </a>
                            </div>

                        </div>
                    </div>

                    <div class="well text-center" id="product-thing">
                        <h4><span class="glyphicon glyphicon-apple"></span> ${productsThings}</h4>
                        <p>${productsThingsInfo}</p>
                        <br>

                        <div class="row">
                            <div class="dashboard-button col-sm-6">
                                <a name="button" value="add-thing-to-product">
                                    <div class="well">
                                        <h4><span class="glyphicon glyphicon-plus"></span></h4>
                                        <p>${btnAdd}</p>

                                    </div>
                                </a>
                            </div>

                            <div class="dashboard-button col-sm-6">
                                <a name="button" value="remove-thing-from-product">
                                    <div class="well">
                                        <h4><span class="glyphicon glyphicon-remove-circle"></span></h4>
                                        <p>${btnDelete}</p>

                                    </div>
                                </a>
                            </div>

                        </div>
                    </div>

                    <div class="well text-center" id="discounts">
                        <h4><span class="glyphicon glyphicon-certificate"></span> ${discounts}</h4>
                        <p>${discountsInfo}</p>
                        <br>
                        <div class="row">

                            <div class="dashboard-button col-sm-4">
                                <a name="button" value="create-discount">
                                    <div class="well">
                                        <h4><span class="glyphicon glyphicon-plus"></span></h4>
                                        <p>${btnCreate}</p>

                                    </div>
                                </a>
                            </div>

                            <div class="dashboard-button col-sm-4">
                                <a name="button" value="update-discount">
                                    <div class="well">
                                        <h4><span class="glyphicon glyphicon-refresh"></span></h4>
                                        <p>${btnUpdate}</p>

                                    </div>
                                </a>
                            </div>

                            <div class="dashboard-button col-sm-4">
                                <a name="button" value="delete-discount">
                                    <div class="well">
                                        <h4><span class="glyphicon glyphicon-remove-circle"></span></h4>
                                        <p>${btnDelete}t</p>

                                    </div>
                                </a>
                            </div>

                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <%@include file="templates/end-page/begin-hidden-connector.jsp" %>
        <%@include file="templates/end-page/hidden-elements/signing.jsp" %>
        <%@include file="templates/end-page/hidden-elements/action-block.jsp" %>
    <%@include file="templates/end-page/end-hidden-connector.jsp" %>

    <script src="resources/js/spin.js"></script>
    <script src="resources/js/dashboard.js"></script>

<%@include file="templates/end-page.jsp" %>