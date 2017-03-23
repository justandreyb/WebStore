<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<c:if test="${sessionScope.locale != null}">
    <fmt:setLocale value="${sessionScope.locale}" />
</c:if>
<c:if test="${sessionScope.locale == null}">
    <fmt:setLocale value="en-EN" />
</c:if>

<fmt:setBundle basename="com.training.web_store.localization.prop" var="curr_locale"/>
