<%@include file="/WEB-INF/jsp/defines/Settings.jsp" %>
<%@include file="/WEB-INF/jsp/defines/entity/Account.jsp" %>
<div class="container-fluid">
    <form class="form-horizontal" method="post">

        <div class="form-group">
            <label class="control-label col-sm-2" for="change-account">${accountsField}</label>
            <div class="col-sm-10">
                <c:set var="accounts" scope="request" value="${entities}" />
                <select class="form-control" id="change-account">
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
        <br>
    </form>
    <div class="form-group">
        <button class="btn btn-default col-xs-3" onclick="handleBlockAccount()">${block}</button>
        <div class="col-xs-2"></div>
        <button class="btn btn-default col-xs-3" onclick="getAccountChangeRoleForm()">${changeRole}</button>
        <div class="col-xs-2"></div>
        <button class="btn btn-default col-xs-3" onclick="handleDeleteBrand()">${delete}</button>
    </div>
</div>
