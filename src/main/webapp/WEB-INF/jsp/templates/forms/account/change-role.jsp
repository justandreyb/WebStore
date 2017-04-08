<%@include file="/WEB-INF/jsp/defines/Settings.jsp" %>
<%@include file="/WEB-INF/jsp/defines/entity/Brand.jsp" %>
<div class="container-fluid">
    <form class="form-horizontal" method="post">

        <div class="form-group">
            <label class="control-label col-sm-2" for="change-role-account">${accountsField}</label>
            <div class="col-sm-10">
                <c:set var="accounts" scope="request" value="${listAccounts}" />
                <select class="form-control" id="change-role-account">
                    <option selected>${none}</option>
                    <c:if test="${accounts != null}">
                        <c:forEach items="${accounts}" var="account">
                            <c:set var="map" scope="request" value="${account.parameters}" />
                            <option value="<c:out value="${map['user_id']}"/>">
                                <c:out value="${map['user_email']}"/> |
                                <c:out value="${map['user_first_name']}"/>
                            </option>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="change-role-role">${rolesField}</label>
            <div class="col-sm-10">
                <c:set var="roles" scope="request" value="${listRoles}" />
                <select class="form-control" id="change-role-role">
                    <option selected>${none}</option>
                    <c:if test="${roles != null}">
                        <c:forEach items="${roles}" var="role">
                            <c:set var="map" scope="request" value="${role.parameters}" />
                            <option>
                                <c:forEach items="${map}" var="roleElement">
                                    <c:out value="${roleElement.value}"/> |
                                </c:forEach>
                            </option>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
        </div>
        <br>
        <div class="form-group">
            <button class="btn btn-default col-md-offset-3 col-md-6 col-md-offset-3 col-xs-offset-1 col-xs-offset-1 col-xs-10 col-sm-offset-2 col-sm-8 col-sm-offset-2" onclick="handleDeleteBrand()">${delete}</button>
        </div>
    </form>
</div>
