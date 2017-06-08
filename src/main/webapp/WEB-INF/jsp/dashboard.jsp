<%@include file="defines/Settings.jsp" %>
<%@include file="defines/DashboardDefines.jsp"%>

<%@include file="templates/head.jsp" %>

<body>
    <div class="wrapper">
        <%@include file="templates/navbar/begin-navbar.jsp" %>
            <%@include file="templates/navbar/accounts/adminAccount.jsp" %>
        <%@include file="templates/navbar/end-navbar.jsp" %>

        <div class="inner">
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
                            <h4>${dashboard}</h4>
                            <p>${dashboardInfo}</p>
                        </div>

                        <div id="profiles">
                            <h4><span class="glyphicon glyphicon-user"></span> ${profiles}</h4>
                            <p>${profilesInfo}</p>
                            <br>
                            <div class="well text-center">
                                <br>
                                <div class="row">

                                    <div class="dashboard-button col-sm-12" onclick="getAccountChangingForm()">
                                        <a>
                                            <div class="well">
                                                <h4><span class="glyphicon glyphicon-ban-circle"></span></h4>
                                                <p>${blockUser}/${changeUserRole}/${btnDelete}</p>

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
                                    <div class="dashboard-button col-sm-6"  onclick="getCategoryAddingForm()">
                                        <a>
                                            <div class="well">
                                                <h4><span class="glyphicon glyphicon-plus"></span></h4>
                                                <p>${btnCreate}</p>

                                            </div>
                                        </a>
                                    </div>

                                    <div class="dashboard-button col-sm-6"  onclick="getCategoryChangingForm()">
                                        <a>
                                            <div class="well">
                                                <h4><span class="glyphicon glyphicon-refresh"></span></h4>
                                                <p>${btnChange}</p>

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
                                    <div class="dashboard-button col-sm-6" onclick="getBrandAddingForm()">
                                        <a>
                                            <div class="well">
                                                <h4><span class="glyphicon glyphicon-plus"></span></h4>
                                                <p>${btnCreate}</p>

                                            </div>
                                        </a>
                                    </div>

                                    <div class="dashboard-button col-sm-6" onclick="getBrandChangingForm()">
                                        <a>
                                            <div class="well">
                                                <h4><span class="glyphicon glyphicon-eye-close"></span></h4>
                                                <p>${btnChange}</p>

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
                                    <div class="dashboard-button col-sm-6"  onclick="getThingAddingForm()">
                                        <a>
                                            <div class="well">
                                                <h4><span class="glyphicon glyphicon-plus"></span></h4>
                                                <p>${btnCreate}</p>

                                            </div>
                                        </a>
                                    </div>

                                    <div class="dashboard-button col-sm-6" onclick="getThingChangingForm()">
                                        <a>
                                            <div class="well">
                                                <h4><span class="glyphicon glyphicon-eye-close"></span></h4>
                                                <p>${btnChange}</p>

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

                                    <div class="dashboard-button col-sm-6" onclick="getProductAddingForm()">
                                        <a>
                                            <div class="well">
                                                <h4><span class="glyphicon glyphicon-plus"></span></h4>
                                                <p>${btnCreate}</p>

                                            </div>
                                        </a>
                                    </div>

                                    <div class="dashboard-button col-sm-6" onclick="getProductChangingForm()">
                                        <a>
                                            <div class="well">
                                                <h4><span class="glyphicon glyphicon-refresh"></span></h4>
                                                <p>${btnChange}</p>

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

                                    <div class="dashboard-button col-sm-6" onclick="getDiscountAddingForm()">
                                        <a>
                                            <div class="well">
                                                <h4><span class="glyphicon glyphicon-plus"></span></h4>
                                                <p>${btnCreate}</p>

                                            </div>
                                        </a>
                                    </div>

                                    <div class="dashboard-button col-sm-6" onclick="getDiscountChangingForm()">
                                        <a>
                                            <div class="well">
                                                <h4><span class="glyphicon glyphicon-remove-circle"></span></h4>
                                                <p>${btnChange}</p>

                                            </div>
                                        </a>
                                    </div>

                                </div>
                            </div>

                        </div>

                    </div>
                </div>
            </div>
        </div>

        <%@include file="templates/end-page/begin-hidden-connector.jsp" %>
            <%@include file="templates/end-page/hidden-elements/action-block.jsp" %>
            <%@include file="templates/end-page/hidden-elements/spinner-block.jsp" %>
        <%@include file="templates/end-page/end-hidden-connector.jsp" %>

        <script src="resources/js/spin.js"></script>
        <script src="resources/js/json-handler.js"></script>
        <script src="resources/js/dashboard.js"></script>
        <script src="resources/js/account-handler.js"></script>
        <script src="resources/js/spinner-handler.js"></script>
    </div>

    <%@include file="templates/end-page/footer.jsp" %>

<%@include file="templates/end-page.jsp" %>