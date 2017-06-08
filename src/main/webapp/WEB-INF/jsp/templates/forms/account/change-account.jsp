<%@include file="/WEB-INF/jsp/defines/Settings.jsp" %>
<%@include file="/WEB-INF/jsp/defines/entity/Account.jsp" %>

<div class="container-fluid">
    <form class="form-horizontal" method="post">

        <div class="form-group">
            <label class="control-label col-sm-2" for="change-account">${accountsField}</label>
            <div class="col-sm-10">
                <select class="form-control" id="change-account">
                    <option value="Not selected" selected>${none}</option>
                    #ACCOUNTS
                </select>
            </div>
        </div>
        <br>
    </form>
    <div class="form-group">
        <button class="btn btn-default col-xs-5" onclick="handleBlockAccount()">${block}</button>
        <div class="col-xs-2"></div>
        <button class="btn btn-default col-xs-5" onclick="getAccountChangeRoleForm()">${changeRole}</button>
    </div>
</div>
