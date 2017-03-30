<%@include file="Settings.jsp" %>
<%@include file="defines/ImDefines.jsp"%>

<%@include file="templates/head.jsp" %>

<body>
    <%@include file="templates/navbar/begin-navbar.jsp" %>
        <%@include file="templates/navbar/accounts/guestAccount.jsp" %>
    <%@include file="templates/navbar/end-navbar.jsp" %>

    <h2>${about}</h2>

    <c:if test="${sessionScope.user != null}">
        <c:set var="user" value="${sessionScope.user}" />

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

        <form method="post">
            <button name="command" value="sign_out">
                ${logout}
            </button>
        </form>

    </c:if>
    <c:if test="${sessionScope.user == null}">
        ${errorNotFound}
    </c:if>

    <%@include file="templates/end-page/begin-hidden-connector.jsp" %>
        <%@include file="templates/end-page/hidden-elements/signing.jsp" %>
    <%@include file="templates/end-page/end-hidden-connector.jsp" %>

<%@include file="templates/end-page.jsp" %>
