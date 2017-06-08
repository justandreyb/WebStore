<%@include file="defines/Settings.jsp" %>
<%@include file="defines/ImDefines.jsp"%>

<%@include file="templates/head.jsp" %>

<body>
    <div class="wrapper">
        <%@include file="templates/navbar/begin-navbar.jsp" %>
            <%@include file="templates/navbar/accounts/userAccount.jsp" %>
        <%@include file="templates/navbar/end-navbar.jsp" %>

            <c:set var="user" value="${sessionScope.user}" />
            <div class="inner">
                <p>
                    Email :
                    <c:out value="${user.email}"/>
                </p>
                <p>
                    ${firstName} :
                    <c:out value="${user.firstName}"/>
                </p>
                <p>
                    ${lastName} :
                    <c:out value="${user.lastName}"/>
                </p>
                <p>
                    ${address} :
                    <c:out value="${user.address}"/></p>
                <p>
                    ${phone} :
                    <c:out value="${user.phoneNumber}"/>
                </p>
                <p>
                    ${gender} :
                    <c:if test="${user.gender.equals('Male')}">
                        ${male}
                    </c:if>
                    <c:if test="${user.gender.equals('Female')}">
                        ${female}
                    </c:if>
                </p>
            </div>
        <%@include file="templates/end-page/begin-hidden-connector.jsp" %>
            <%@include file="templates/end-page/hidden-elements/action-block.jsp" %>
            <%@include file="templates/end-page/hidden-elements/spinner-block.jsp" %>
        <%@include file="templates/end-page/end-hidden-connector.jsp" %>

        <script src="resources/js/spin.js"></script>
        <script src="resources/js/spinner-handler.js"></script>
        <script src="resources/js/json-handler.js"></script>
        <script src="resources/js/account-handler.js"></script>
    </div>
    <%@include file="templates/end-page/footer.jsp" %>

<%@include file="templates/end-page.jsp" %>
