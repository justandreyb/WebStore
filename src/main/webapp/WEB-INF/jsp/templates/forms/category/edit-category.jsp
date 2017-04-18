<%@include file="/WEB-INF/jsp/defines/Settings.jsp" %>
<%@include file="/WEB-INF/jsp/defines/entity/Category.jsp" %>
<div class="container-fluid">
    <form class="form-horizontal" method="post">

        <c:set var="categoryEntity" scope="request" value="${category}" />

        <div class="form-group">
            <label class="control-label col-sm-2" for="edit-category-name">${entityName}</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="edit-category-name" placeholder="${enterName}" value="${categoryEntity.name}">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="edit-category-description">${entityDescription}:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="edit-category-description" placeholder="${enterDescription}" value="${categoryEntity.description}>
            </div>
        </div>

    </form>
    <div class="form-group">
        <br>
        <button class="btn btn-default col-md-offset-3 col-md-6 col-md-offset-3 col-xs-offset-1 col-xs-offset-1 col-xs-10 col-sm-offset-2 col-sm-8 col-sm-offset-2" onclick="handleEditCategory()">${edit}</button>
    </div>
</div>
