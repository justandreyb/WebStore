<%@include file="/WEB-INF/jsp/defines/Settings.jsp" %>
<%@include file="/WEB-INF/jsp/defines/entity/Category.jsp" %>
<div class="container-fluid">
    <form class="form-horizontal" method="post">

        <div class="form-group">
            <label class="control-label col-sm-2" for="change-category">${entityName}</label>
            <div class="col-sm-10">
                <select class="form-control" id="change-category">
                    <option selected>${none}</option>
                    #CATEGORIES
                </select>
            </div>
        </div>
        <br>
    </form>
    <div class="row">
        <button class="btn btn-default col-xs-5" onclick="getCategoryEditingForm()">${edit}</button>
        <div class="col-xs-2"></div>
        <button class="btn btn-default col-xs-5" onclick="handleDeleteCategory()">${delete}</button>
    </div>
</div>
