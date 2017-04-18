<c:if test="${sessionScope.user != null}">
    <%@include file="accounts/userAccount.jsp" %>
</c:if>

<c:if test="${sessionScope.user == null}">

    <c:if test="${sessionScope.admin != null}">
        <%@include file="accounts/adminAccount.jsp" %>
    </c:if>

    <c:if test="${sessionScope.admin == null}">
        <%@include file="accounts/guestAccount.jsp" %>
    </c:if>

</c:if>
