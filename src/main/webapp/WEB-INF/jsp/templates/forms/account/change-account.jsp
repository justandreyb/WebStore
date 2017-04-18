<%@include file="/WEB-INF/jsp/defines/Settings.jsp" %>
<%@include file="/WEB-INF/jsp/defines/entity/Account.jsp" %>
<div class="container-fluid">
    <form class="form-horizontal" method="post">

        <div class="form-group">
            <label class="control-label col-sm-2" for="change-account">${accountsField}</label>
            <div class="col-sm-10">
                <c:set var="data" value="${requestScope.collectedData}" />
                <select class="form-control" id="change-account">
                    <option value="Not selected" selected>${none}</option>
                    <c:set var="accounts" value="${data.accounts}" />
                    <c:if test="${accounts != null}">
                        <c:forEach items="${accounts}" var="account">
                            <option value="<c:out value="${account.id}"/>">
                                <c:out value="${account.email}"/> |
                                <c:out value="${account.firstName}"/>
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
        <button class="btn btn-default col-xs-3" onclick="handleDeleteAccount()">${delete}</button>
    </div>
</div>
