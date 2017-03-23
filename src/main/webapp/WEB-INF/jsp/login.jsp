<%@include file="Settings.jsp" %>
<%@include file="defines/LoginDefines.jsp"%>

<%@include file="templates/head.jsp" %>

<body>
    <%@include file="templates/navbar/begin-navbar.jsp" %>
        <%@include file="templates/navbar/accounts/guestAccount.jspx" %>
    <%@include file="templates/navbar/end-navbar.jsp" %>

    <h2>${about}</h2>

    <c:if test="${sessionScope.user != null}">
        <p>${errorSigned}</p>
    </c:if>

    <c:if test="${sessionScope.user == null}">
        <form method="post">
            <label>Email</label>
            <input type="input" name="email">

            <label>${password}</label>
            <input type="password" name="password">

            <button type="submit" name="command" value="sign_in">
                ${signIn}
            </button>
            <button>
                <a href="/welcome">${cancel}</a>
            </button>
        </form>
    </c:if>

<%@include file="templates/end-page/begin-hidden-connector.jsp" %>
    <%@include file="templates/end-page/hidden-elements/signing.jsp" %>
<%@include file="templates/end-page/end-hidden-connector.jsp" %>

<%@include file="templates/end-page.jsp" %>

