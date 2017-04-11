<%@include file="/WEB-INF/jsp/defines/Settings.jsp" %>
<%@include file="/WEB-INF/jsp/defines/entity/Account.jsp" %>
<div class="container-fluid">
    <form class="form-horizontal" method="post">

        <div class="form-group">
            <div class="col-sm-10">
                <c:set var="accountEntity" scope="request" value="${account}" />
                <div class="form-group">
                    <label class="control-label col-sm-2">${accountField}</label>
                    <label class="control-label col-sm-10" id="change-role">${accountEntity}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="role-id">${rolesField}</label>
            <div class="col-sm-10">
                <c:set var="roles" scope="request" value="${entities}" />
                <select class="form-control" id="role-id">
                    <option value="Not selected" selected>${none}</option>
                    <c:if test="${roles != null}">
                        <c:forEach items="${roles}" var="role">
                            <c:set var="map" scope="request" value="${role.parameters}" />
                            <option value="<c:out value="${map['role_id']}"/>">
                                <c:out value="${map['role_value']}"/>
                            </option>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
        </div>
        <br>
        <div class="form-group">
            <button class="btn btn-default col-md-offset-3 col-md-6 col-md-offset-3 col-xs-offset-1 col-xs-offset-1 col-xs-10 col-sm-offset-2 col-sm-8 col-sm-offset-2" onclick="handleChangeAccountRole()">${changeRole}</button>
        </div>
    </form>
</div>
