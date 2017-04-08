<%@include file="/WEB-INF/jsp/defines/Settings.jsp" %>
<%@include file="/WEB-INF/jsp/defines/entity/Product.jsp" %>
<div class="container-fluid">
    <form class="form-horizontal" method="post">

        <div class="form-group">
            <label class="control-label col-sm-2" for="change-product">${entityName}</label>
            <div class="col-sm-10">
                <c:set var="products" scope="request" value="${entities}" />
                <select class="form-control" id="change-product">
                    <option selected>${none}</option>
                    <c:if test="${products != null}">
                        <c:forEach items="${products}" var="product">
                            <c:set var="map" scope="request" value="${product.parameters}" />
                            <option value="<c:out value="${map['product_id']}"/>">
                                <c:out value="${map['product_name']}"/>
                            </option>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
        </div>
        <br>
    </form>
    <div class="row">
        <button class="btn btn-default col-xs-5" onclick="getProductChangingForm()">${edit}</button>
        <div class="col-xs-2"></div>
        <button class="btn btn-default col-xs-5" onclick="handleDeleteProduct()">${delete}</button>
    </div>
</div>
