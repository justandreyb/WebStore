<%@include file="/WEB-INF/jsp/defines/Settings.jsp" %>
<%@include file="/WEB-INF/jsp/defines/entity/Image.jsp" %>
        <div class="container-fluid">
            <form class="form-horizontal" method="post">

                <input type="hidden" id="add-image-product-id" value="#PRODUCT_ID" />
                <input type="hidden" id="add-image-thing-id" value="#THING_ID" />

                <div class="form-group">
                    <label class="control-label col-sm-2" for="add-image-href">${entityHref}</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="add-image-href" placeholder="${enterHref}">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="add-image-real-name">${realName}</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="add-image-real-name" placeholder="${enterRealName}">
                    </div>
                </div>

            </form>
            <div class="form-group">
                <br>
                <button class="btn btn-default col-md-offset-3 col-md-6 col-md-offset-3 col-xs-offset-1 col-xs-offset-1 col-xs-10 col-sm-offset-2 col-sm-8 col-sm-offset-2" onclick="handleAddPhoto()">${add}</button>
            </div>
        </div>