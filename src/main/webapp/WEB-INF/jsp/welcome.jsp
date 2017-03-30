<%@include file="Settings.jsp"%>
<%@include file="defines/WelcomeDefines.jsp"%>

<%@include file="templates/head.jsp" %>
<body>
    <%@include file="templates/navbar/begin-navbar.jsp" %>
        <%@include file="templates/navbar/accounts/guestAccount.jsp" %>
    <%@include file="templates/navbar/end-navbar.jsp" %>

    <h2>${about}</h2>
    <form method="post">
        <button title="Sign in">
            <a href="/login">
                ${signIn}
            </a>
        </button>
        <button title="Sign up">
            <a href="/registration">
                ${signUp}
            </a>
        </button>
    </form>

    <%@include file="templates/end-page/begin-hidden-connector.jsp" %>
        <%@include file="templates/end-page/hidden-elements/signing.jsp" %>
    <%@include file="templates/end-page/end-hidden-connector.jsp" %>

<%@include file="templates/end-page.jsp" %>
