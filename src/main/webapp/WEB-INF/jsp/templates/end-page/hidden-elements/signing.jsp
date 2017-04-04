<c:if test="${sessionScope.user == null}">
    <c:if test="${sessionScope.admin == null}">
        <%@include file="/WEB-INF/jsp/defines/RegistrationDefines.jsp"%>
        <div class="container-fluid signing">
            <!-- Modal -->
            <div id="signing" class="modal fade" role="dialog">
                <div class="modal-dialog">
                    <!-- Modal content-->
                    <div class="modal-content">
                        <!-- Modal header-->
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">${account}</h4>
                        </div>
                        <!-- Modal body-->
                        <div class="modal-body">
                            <ul class="nav nav-tabs">
                                <li class="col-lg-6 col-sm-6 col-md-6 col-xs-6"><a data-toggle="tab" href="#sign-in">${signIn}</a></li>
                                <li class="active col-lg-6 col-sm-6 col-md-6 col-xs-6"><a data-toggle="tab" href="#sign-up">${signUp}</a></li>
                            </ul>

                            <div class="tab-content">
                                <div id="sign-up" class="tab-pane fade in active">
                                    <br>
                                    <div class="container-fluid">
                                        <form class="form-horizontal" method="post">
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" for="register-email">Email:</label>
                                                <div class="col-sm-10">
                                                    <input type="email" class="form-control" id="register-email" name="email" placeholder="${enterEmail}">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="control-label col-sm-2" for="register-password">${password}:</label>
                                                <div class="col-sm-10">
                                                    <input type="password" class="form-control" id="register-password" placeholder="${enterPassword}">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="control-label col-sm-2" for="register-first-name">${firstName}</label>
                                                <div class="col-sm-10">
                                                    <input type="input" class="form-control" id="register-first-name" name="first_name" placeholder="${enterFirstName}">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="control-label col-sm-2" for="register-last-name">${lastName}</label>
                                                <div class="col-sm-10">
                                                    <input type="input" class="form-control" id="register-last-name" name="last_name" placeholder="${enterLastName}">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="control-label col-sm-2" for="register-gender">${gender}</label>
                                                <div class="col-sm-10">
                                                    <select class="form-control" name="gender" id="register-gender"> <option>${genderUndefined}</option>
                                                        <option>${male}</option>
                                                        <option>${female}</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="control-label col-sm-2" for="register-language">${language}</label>
                                                <div class="col-sm-10">
                                                    <select class="form-control" name="language" id="register-language">
                                                        <option selected value="en-EN">${english}</option>
                                                        <option value="ru-RU">${russian}</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="control-label col-sm-2" for="register-phone">${phone}</label>
                                                <div class="col-sm-10">
                                                    <input type="input" class="form-control" id="register-phone" name="phone_number" placeholder="${enterPhone}">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="control-label col-sm-2" for="register-address">${address}</label>
                                                <div class="col-sm-10">
                                                    <input type="input" class="form-control" id="register-address" name="address" placeholder="${enterAddress}">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <button class="btn btn-default col-md-offset-3 col-md-6 col-md-offset-3 col-xs-offset-1 col-xs-10 col-xs-offset-1 col-sm-offset-1 col-sm-10 col-sm-offset-1" onclick="signUp()">${signUp}</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <div id="sign-in" class="tab-pane fade">
                                    <div class="container-fluid">
                                        <br>
                                        <form class="form-horizontal" method="post">

                                            <div class="form-group">
                                                <label class="control-label col-sm-2" for="login-email">Email:</label>
                                                <div class="col-sm-10">
                                                    <input type="email" class="form-control" id="login-email" name="email" placeholder="${enterEmail}">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="control-label col-sm-2" for="login-password">${password}:</label>
                                                <div class="col-sm-10">
                                                    <input type="password" class="form-control" id="login-password" placeholder="${enterPassword}">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <br>
                                                <button type="submit" class="btn btn-default col-md-offset-3 col-md-6 col-md-offset-3 col-xs-offset-1 col-xs-offset-1 col-xs-10 col-sm-offset-2 col-sm-8 col-sm-offset-2" onclick="signIn()">${signIn}</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <!-- Modal footer-->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">${cancel}</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</c:if>