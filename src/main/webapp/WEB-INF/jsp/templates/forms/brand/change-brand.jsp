<%@include file="/WEB-INF/jsp/defines/Settings.jsp" %>
<%@include file="/WEB-INF/jsp/defines/entity/Brand.jsp" %>
        <div class="container-fluid">
            <form class="form-horizontal" method="post">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="change-brand">${entityTitle}</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="change-brand">
                            <option value="Not selected" selected>${none}</option>
                            #BRANDS
                        </select>
                    </div>
                </div>
                <br>

            </form>
            <div class="row">
                <button class="btn btn-default col-xs-5" onclick="getBrandEditingForm()">${edit}</button>
                <div class="col-xs-2"></div>
                <button class="btn btn-default col-xs-5" onclick="handleDeleteBrand()">${delete}</button>
            </div>
        </div>
