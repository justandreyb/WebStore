<%@include file="/WEB-INF/jsp/defines/Settings.jsp" %>
<%@include file="/WEB-INF/jsp/defines/entity/Brand.jsp" %>
<div class="container-fluid">
    <form class="form-horizontal" method="post">

        <div class="form-group">
            <label class="control-label col-sm-2" for="change-role-account">${brandName}</label>
            <div class="col-sm-10">
                <select class="form-control" id="change-role-account">
                    <option selected>${none}</option>
                    <%--TODO : Foreach--%>
                    <option>Brand 1</option>
                    <option>Brand 2</option>
                </select>
            </div>
        </div>
        <br>
        <div class="form-group">
            <button class="btn btn-default col-md-offset-3 col-md-6 col-md-offset-3 col-xs-offset-1 col-xs-offset-1 col-xs-10 col-sm-offset-2 col-sm-8 col-sm-offset-2" onclick="handleDeleteBrand()">${delete}</button>
        </div>
    </form>
</div>
