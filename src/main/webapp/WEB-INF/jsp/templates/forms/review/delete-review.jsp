<%@include file="/WEB-INF/jsp/defines/Settings.jsp" %>
<%@include file="/WEB-INF/jsp/defines/entity/Review.jsp" %>
<div class="container-fluid">
    <form class="form-horizontal" method="post">

        <div class="form-group">
            <label class="control-label col-sm-2" for="delete-review">${entityName}</label>
            <div class="col-sm-10">
                <c:set var="reviews" scope="request" value="${entities}" />
                <select class="form-control" id="delete-review">
                    <option selected>${none}</option>
                    <c:if test="${reviews != null}">
                        <c:forEach items="${reviews}" var="review">
                            <c:set var="map" scope="request" value="${review.parameters}" />
                            <option value="<c:out value="${map['brand_id']}"/>">
                                <c:out value="${map['brand_id']}"/>
                            </option>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
        </div>
        <br>
    </form>
    <div class="form-group">
        <button class="btn btn-default col-md-offset-3 col-md-6 col-md-offset-3 col-xs-offset-1 col-xs-offset-1 col-xs-10 col-sm-offset-2 col-sm-8 col-sm-offset-2" onclick="handleDeleteBrand()">${delete}</button>
    </div>
</div>
