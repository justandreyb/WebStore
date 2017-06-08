<%@include file="/WEB-INF/jsp/defines/Settings.jsp" %>
<%@include file="/WEB-INF/jsp/defines/entity/Product.jsp" %>
<div class="container-fluid">
    <form class="form-horizontal" method="post">
        <div class="form-group">
            <label class="control-label col-sm-2" for="change-product">${entityName}</label>
            <div class="col-sm-10">
                <select class="form-control" id="change-product">
                    <option selected>${none}</option>
                    #PRODUCTS
                </select>
            </div>
        </div>
        <br>
    </form>
    <div class="row">
        <button class="btn btn-default col-xs-5" onclick="getProductEditingForm()">${edit}</button>
        <div class="col-xs-2"></div>
        <button class="btn btn-default col-xs-5" onclick="handleDeleteProduct()">${delete}</button>
        <br>
        <button class="btn btn-default col-xs-5" onclick="getPhotoAddingForm()">${addPhoto}</button>
        <div class="col-xs-2"></div>
        <button class="btn btn-default col-xs-5" onclick="getPhotoDeletingForm()">${deletePhoto}</button>
    </div>
</div>
