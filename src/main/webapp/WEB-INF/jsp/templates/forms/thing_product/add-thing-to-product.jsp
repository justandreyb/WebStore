<%@include file="/WEB-INF/jsp/defines/Settings.jsp" %>
<%@include file="/WEB-INF/jsp/defines/entity/Thing_Product.jsp" %>
<div class="container-fluid">
    <form class="form-horizontal" method="post">

        <input type="hidden" id="add-thing-to-product-product" value="#PRODUCT_ID">

        <div class="form-group">
            <label class="control-label col-sm-2" for="add-thing-to-product-thing">${entityThing}</label>
            <div class="col-sm-10">
                <select class="form-control" id="add-thing-to-product-thing">
                    #THINGS
                </select>
            </div>
        </div>

    </form>
    <div class="form-group">
        <br>
        <button class="btn btn-default col-md-offset-3 col-md-6 col-md-offset-3 col-xs-offset-1 col-xs-offset-1 col-xs-10 col-sm-offset-2 col-sm-8 col-sm-offset-2" onclick="handleAddThingToProduct()">${add}</button>
    </div>
</div>