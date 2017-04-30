<%@include file="/WEB-INF/jsp/defines/Settings.jsp" %>
<%@include file="/WEB-INF/jsp/defines/entity/Product.jsp" %>
        <div class="container-fluid">
            <form class="form-horizontal" method="post">

                <input type="hidden" id="edit-product-id" value="#ID" />

                <div class="form-group">
                    <label class="control-label col-sm-2" for="edit-product-name">${entityName}</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="edit-product-name" placeholder="${enterName}" value="#NAME">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="edit-product-category-id">${entityCategory}</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="edit-product-category-id">
                            #CATEGORIES
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="edit-product-discount-id">${entityDiscount}</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="edit-product-discount-id">
                            <option selected value="0">${none}</option>
                            #DISCOUNTS
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="edit-product-price">${entityPrice}</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" id="edit-product-price" placeholder="${enterPrice}" value="#PRICE">
                    </div>
                </div>

            </form>
            <div class="form-group">
                <br>
                <button class="btn btn-default col-md-offset-3 col-md-6 col-md-offset-3 col-xs-offset-1 col-xs-offset-1 col-xs-10 col-sm-offset-2 col-sm-8 col-sm-offset-2" onclick="handleEditProduct()">${edit}</button>
                <br>
                <button class="btn btn-default col-xs-5" onclick="getThingAddingToProductForm()">${addThing}</button>
                <div class="col-xs-2"></div>
                <button class="btn btn-default col-xs-5" onclick="getThingDeletingFromProductForm()">${deleteThing}</button>
                <br>
            </div>
        </div>
