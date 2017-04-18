<%@include file="defines/Settings.jsp"%>
<%--TODO: Product defines--%>
<%@include file="defines/ProductDefines.jsp"%>

<%@include file="templates/head.jsp" %>

<body>
    <%@include file="templates/navbar/begin-navbar.jsp" %>
        <%@include file="templates/navbar/role-selector.jsp" %>
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
        <%@include file="templates/end-page/hidden-elements/action-block.jsp" %>
    <%@include file="templates/end-page/end-hidden-connector.jsp" %>

    <%@include file="templates/end-page/footer.jsp" %>

    <script src="resources/js/spin.js"></script>
    <script src="resources/js/spinner-handler.js"></script>
    <script src="resources/js/json-handler.js"></script>
    <script src="resources/js/account-handler.js"></script>

<%@include file="templates/end-page.jsp" %>
