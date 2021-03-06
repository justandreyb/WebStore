<%@include file="/WEB-INF/jsp/defines/Settings.jsp" %>
<%@include file="/WEB-INF/jsp/defines/entity/Thing.jsp" %>
        <div class="container-fluid">
            <form class="form-horizontal" method="post">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="change-thing">${entityTitle}</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="change-thing">
                            <option selected>${none}</option>
                            #THINGS
                        </select>
                    </div>
                </div>
                <br>
            </form>
            <div class="row">
                <button class="btn btn-default col-xs-5" onclick="getThingEditingForm()">${edit}</button>
                <div class="col-xs-2"></div>
                <button class="btn btn-default col-xs-5" onclick="handleDeleteThing()">${delete}</button>
                <br class="col-xs-12">
                <button class="btn btn-default col-xs-5" onclick="getPhotoAddingForm()">${addPhoto}</button>
                <div class="col-xs-2"></div>
                <button class="btn btn-default col-xs-5" onclick="getPhotoDeletingForm()">${deletePhoto}</button>
                <br>
                <button class="btn btn-default col-xs-12" onclick="getReviewAddingForm()">${addReview}</button>
            </div>
        </div>
