<%@include file="/WEB-INF/jsp/defines/Settings.jsp" %>
<%@include file="/WEB-INF/jsp/defines/entity/Thing.jsp" %>
        <div class="container-fluid">
            <form class="form-horizontal" method="post">

                <div class="form-group">
                    <label class="control-label col-sm-2" for="add-thing-name">${entityName}</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="add-thing-name" placeholder="${enterName}">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="add-thing-category-id">${entityCategory}</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="add-thing-category-id">
                            #CATEGORIES
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="add-thing-brand-id">${entityBrand}</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="add-thing-brand-id">
                            #BRANDS
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="add-thing-creation-date">${entityCreationDate}</label>
                    <div class="col-sm-10">
                        <input type="date" class="form-control" id="add-thing-creation-date" placeholder="${enterCreationDate}">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="add-thing-description">${entityDescription}</label>
                    <div class="col-sm-10">
                        <textarea class="col-xs-12" id="add-thing-description" maxlength="254"></textarea>
                    </div>
                </div>

            </form>
            <div class="form-group">
                <br>
                <button class="btn btn-default col-md-offset-3 col-md-6 col-md-offset-3 col-xs-offset-1 col-xs-offset-1 col-xs-10 col-sm-offset-2 col-sm-8 col-sm-offset-2" onclick="handleAddThing()">${add}</button>
            </div>
        </div>