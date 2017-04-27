<%@include file="/WEB-INF/jsp/defines/Settings.jsp" %>
<%@include file="/WEB-INF/jsp/defines/entity/Category.jsp" %>
        <div class="container-fluid">
            <form class="form-horizontal" method="post">

                <input type="hidden" id="add-review-thing-id" value="#THING_ID" />

                <div class="form-group">
                    <textarea maxlength="5000" id="add-review-text"></textarea>
                </div>

            </form>
            <div class="form-group">
                <br>
                <button class="btn btn-default col-md-offset-3 col-md-6 col-md-offset-3 col-xs-offset-1 col-xs-offset-1 col-xs-10 col-sm-offset-2 col-sm-8 col-sm-offset-2" onclick="handleAddReview()">${add}</button>
            </div>
        </div>

