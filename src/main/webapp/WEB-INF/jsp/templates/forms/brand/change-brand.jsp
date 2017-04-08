<%@include file="/WEB-INF/jsp/defines/Settings.jsp" %>
<%@include file="/WEB-INF/jsp/defines/entity/Brand.jsp" %>
        <div class="container-fluid">
            <form class="form-horizontal" method="post">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="change-brand">${entityTitle}</label>
                    <div class="col-sm-10">
                        <c:set var="brands" scope="request" value="${entities}" />
                        <select class="form-control" id="change-brand">
                            <option selected>${none}</option>
                            <c:if test="${brands != null}">
                                <c:forEach items="${brands}" var="brand">
                                    <c:set var="map" scope="request" value="${brand.parameters}" />
                                    <option value="<c:out value="${map['brand_id']}"/>">
                                        <c:out value="${map['brand_name']}"/>
                                    </option>
                                </c:forEach>
                            </c:if>
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
