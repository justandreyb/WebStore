<%@include file="/WEB-INF/jsp/defines/Settings.jsp" %>
<%@include file="/WEB-INF/jsp/defines/entity/Brand.jsp" %>
<div class="container-fluid">
    <form class="form-horizontal" method="post">

        <div class="form-group">
            <label class="control-label col-sm-2" for="delete-brand">${entityName}</label>
            <div class="col-sm-10">
                <c:set var="accounts" scope="request" value="${entities}" />
                <select class="form-control" id="delete-brand">
                    <option selected>${none}</option>
                    <c:if test="${accounts != null}">
                        <c:forEach items="${accounts}" var="account">
                            <c:set var="map" scope="request" value="${account.parameters}" />
                            <option>
                                <c:out value="${map['user_id']}"/> |
                                <c:out value="${map['user_email']}"/> |
                                <c:out value="${map['user_first_name']}"/>
                            </option>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
        </div>
        <br>
        <div class="form-group">
            <button class="btn btn-default col-md-offset-3 col-md-6 col-md-offset-3 col-xs-offset-1 col-xs-offset-1 col-xs-10 col-sm-offset-2 col-sm-8 col-sm-offset-2" onclick="handleBlockAccount()">${block}</button>
        </div>
    </form>
</div>
