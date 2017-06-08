<%@include file="/WEB-INF/jsp/defines/Settings.jsp" %>
<%@include file="/WEB-INF/jsp/defines/entity/Rating.jsp" %>
        <div class="container-fluid">

            <form class="form-horizontal" method="post">

                <input type="hidden" id="set-rating-thing-id" value="#THING_ID" />

                <div class="form-group">
                    <label class="control-label col-sm-2" for="set-rating-value">${entityValue}</label>
                    <div class="col-sm-10">
                        <input type="range" min="1" max="100" class="form-control" id="set-rating-value">
                    </div>
                </div>

            </form>
            <div class="form-group">
                <br>
                <button class="btn btn-default col-md-offset-3 col-md-6 col-md-offset-3 col-xs-offset-1 col-xs-offset-1 col-xs-10 col-sm-offset-2 col-sm-8 col-sm-offset-2" onclick="handleSetRating()">${add}</button>
            </div>
        </div>
