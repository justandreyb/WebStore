<%@include file="/WEB-INF/jsp/defines/Settings.jsp" %>
<%@include file="/WEB-INF/jsp/defines/entity/Discount.jsp" %>
        <div class="container-fluid">
            <form class="form-horizontal" method="post">

                <input type="hidden" id="edit-discount-id" value="#ID" />

                <div class="form-group">
                    <label class="control-label col-sm-2" for="edit-discount-value">${entityValue}</label>
                    <div class="col-sm-10">
                        <input type="number" maxlength="2" class="form-control" id="edit-discount-value" placeholder="${enterValue}" value="#VALUE">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="edit-discount-start-date">${entityStartDate}</label>
                    <div class="col-sm-10">
                        <input type="date" class="form-control" id="edit-discount-start-date" placeholder="${enterStartDate}" value="#START_DATE">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="edit-discount-finish-date">${entityFinishDate}</label>
                    <div class="col-sm-10">
                        <input type="date" class="form-control" id="edit-discount-finish-date" placeholder="${enterFinishDate}" value="#FINISH_DATE">
                    </div>
                </div>

            </form>
            <div class="form-group">
                <br>
                <button class="btn btn-default col-md-offset-3 col-md-6 col-md-offset-3 col-xs-offset-1 col-xs-offset-1 col-xs-10 col-sm-offset-2 col-sm-8 col-sm-offset-2" onclick="handleEditDiscount()">${edit}</button>
            </div>
        </div>

