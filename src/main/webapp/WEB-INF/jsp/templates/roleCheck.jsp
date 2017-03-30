<c:if test="${sessionScope.user != null}">
    <jsp:include page="templates/navbar/accounts/userAccount.jsp" />
</c:if>

<c:if test="${sessionScope.user == null}">

    <c:if test="${sessionScope.admin != null}">
        <jsp:include page="templates/navbar/accounts/adminAccount.jsp" />
    </c:if>

    <c:if test="${sessionScope.admin == null}">
        <jsp:include page="templates/navbar/accounts/guestAccount.jsp" />
    </c:if>

</c:if>
