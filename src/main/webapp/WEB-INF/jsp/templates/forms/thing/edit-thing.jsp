<%@include file="/WEB-INF/jsp/defines/Settings.jsp" %>
<%@include file="/WEB-INF/jsp/defines/entity/Thing.jsp" %>
        <div class="container-fluid">
            <form class="form-horizontal" method="post">

                <input type="hidden" id="edit-thing-id" value="#ID" />

                <div class="form-group">
                    <label class="control-label col-sm-2" for="edit-thing-name">${entityName}</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="edit-thing-name" placeholder="${enterName}" value="#NAME">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="edit-thing-category-id">${entityCategory}</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="edit-thing-category-id">
                            #CATEGORIES
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="edit-thing-brand-id">${entityBrand}</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="edit-thing-brand-id">
                            #BRANDS
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="edit-thing-creation-date">${entityCreationDate}</label>
                    <div class="col-sm-10">
                        <input type="date" class="form-control" id="edit-thing-creation-date" placeholder="${enterCreationDate}" value="#CREATION_DATE">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="edit-thing-description">${entityDescription}</label>
                    <div class="col-sm-10">
                        <textarea id="edit-thing-description" maxlength="254">#DESCRIPTION</textarea>
                    </div>
                </div>

            </form>
            <div class="form-group">
                <br>
                <button class="btn btn-default col-md-offset-3 col-md-6 col-md-offset-3 col-xs-offset-1 col-xs-offset-1 col-xs-10 col-sm-offset-2 col-sm-8 col-sm-offset-2" onclick="handleEditThing()">${edit}</button>
            </div>
        </div>
