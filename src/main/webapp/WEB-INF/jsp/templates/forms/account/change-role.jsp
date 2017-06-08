<%@include file="/WEB-INF/jsp/defines/Settings.jsp" %>
<%@include file="/WEB-INF/jsp/defines/entity/Account.jsp" %>
<div class="container-fluid">
    <form class="form-horizontal" method="post">

        <input type="hidden" id="change-role-id" value="#ID" />

        <div class="form-group">
            <label class="control-label col-sm-2" for="change-role-role-id">${rolesField}</label>
            <div class="col-sm-10">
                <select class="form-control" id="change-role-role-id">
                    #ROLES
                </select>
            </div>
        </div>
        <br>
        <div class="form-group">
            <button class="btn btn-default col-md-offset-3 col-md-6 col-md-offset-3 col-xs-offset-1 col-xs-offset-1 col-xs-10 col-sm-offset-2 col-sm-8 col-sm-offset-2" onclick="handleChangeAccountRole()">${changeRole}</button>
        </div>
    </form>
</div>
