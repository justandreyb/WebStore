<%@include file="/WEB-INF/jsp/defines/Settings.jsp" %>
<%@include file="/WEB-INF/jsp/defines/entity/Brand.jsp" %>
<div class="container-fluid">
    <form class="form-horizontal" method="post">

        <input type="hidden" id="edit-brand-id" value="#ID" />

        <div class="form-group">
            <label class="control-label col-sm-2" for="edit-brand-name">${entityName}</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="edit-brand-name" name="add-brand-name" placeholder="${enterName}" value="#NAME">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="edit-brand-description">${entityDescription}</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="edit-brand-description" placeholder="${enterDescription}" value="#DESCRIPTION">
            </div>
        </div>

    </form>
    <div class="form-group">
        <br>
        <button class="btn btn-default col-md-offset-3 col-md-6 col-md-offset-3 col-xs-offset-1 col-xs-offset-1 col-xs-10 col-sm-offset-2 col-sm-8 col-sm-offset-2" onclick="handleEditBrand()">${edit}</button>
    </div>
</div>
