<%@include file="/WEB-INF/jsp/defines/Settings.jsp" %>
<%@include file="/WEB-INF/jsp/defines/entity/Image.jsp" %>
<div class="container-fluid">
    <form class="form-horizontal" method="post">

        <input type="hidden" id="delete-image-product-id" value="#PRODUCT_ID" />
        <input type="hidden" id="delete-image-thing-id" value="#THING_ID" />

        <div class="form-group">
            <label class="control-label col-sm-2" for="delete-image-image-id">${entityHref}</label>
            <div class="col-sm-10">
                <select class="form-control" id="delete-image-image-id">
                    <option selected>${none}</option>
                    #PHOTOS
                </select>
            </div>
        </div>
        <br>
    </form>
    <div class="form-group">
        <button class="btn btn-default col-md-offset-3 col-md-6 col-md-offset-3 col-xs-offset-1 col-xs-offset-1 col-xs-10 col-sm-offset-2 col-sm-8 col-sm-offset-2" onclick="handleDeletePhoto()">${delete}</button>
    </div>
</div>
