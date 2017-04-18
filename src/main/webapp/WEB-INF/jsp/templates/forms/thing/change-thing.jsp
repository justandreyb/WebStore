<%@include file="/WEB-INF/jsp/defines/Settings.jsp" %>
<%@include file="/WEB-INF/jsp/defines/entity/Brand.jsp" %>
        <div class="container-fluid">
            <form class="form-horizontal" method="post">

                <div class="form-group">
                    <label class="control-label col-sm-2" for="change-thing">${entityName}</label>
                    <div class="col-sm-10">
                        <c:set var="things" scope="request" value="${entities}" />
                        <select class="form-control" id="change-thing">
                            <option selected>${none}</option>
                            <c:if test="${things != null}">
                                <c:forEach items="${things}" var="thing">
                                    <c:set var="map" scope="request" value="${thing.parameters}" />
                                    <option value="<c:out value="${map['thing_id']}"/>">
                                        <c:out value="${map['thing_name']}"/> |
                                        <c:out value="${map['thing_category']}"/>
                                    </option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>
                </div>
                <br>
            </form>
            <div class="form-group">
                <button class="btn btn-default col-xs-3" onclick="getRatingSettingForm()">${setRating}</button>
                <div class="col-xs-2"></div>
                <button class="btn btn-default col-xs-3" onclick="getThingEditingForm()">${editProduct}</button>
                <div class="col-xs-2"></div>
                <button class="btn btn-default col-xs-3" onclick="handleDeleteBrand()">${delete}</button>
            </div>
            <div class="row">
                <button class="btn btn-default col-xs-5" onclick="getThingAddingToProductForm()">${addToProduct}</button>
                <div class="col-xs-2"></div>
                <button class="btn btn-default col-xs-5" onclick="getThingDeletingFromProductForm()">${deleteFromProduct}</button>
            </div>
            <div class="row">
                <button class="btn btn-default col-xs-5" onclick="getPhotoAddingForm()">${addImage}</button>
                <div class="col-xs-2"></div>
                <button class="btn btn-default col-xs-5" onclick="getReviewAddingForm()">${addReview}</button>
            </div>
        </div>
