<%@include file="/WEB-INF/jsp/defines/Settings.jsp" %>
<%@include file="/WEB-INF/jsp/defines/entity/Product.jsp" %>
        <div class="container-fluid">
            <form class="form-horizontal" method="post">

                <div class="form-group">
                    <label class="control-label col-sm-2" for="add-brand-name">${entityName}</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="add-brand-name" name="add-brand-name" placeholder="${enterName}">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="add-brand-description">${entityDescription}:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="add-brand-description" placeholder="${enterDescription}">
                    </div>
                </div>

            </form>
        </div>
        <div class="form-group">
            <br>
            <button class="btn btn-default col-md-offset-3 col-md-6 col-md-offset-3 col-xs-offset-1 col-xs-offset-1 col-xs-10 col-sm-offset-2 col-sm-8 col-sm-offset-2" onclick="handleAddCategory()">${add}</button>
        </div>
