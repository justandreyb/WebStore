<%@include file="template/Settings.jsp"%>

<html>
<head>
    <title><fmt:message key="names.titleLogin" bundle="${locale_prop}" /></title>
</head>
<body>
    <h2><fmt:message key="names.aboutLogin" bundle="${locale_prop}" /></h2>
    <c:if test="${sessionScope.user != null}">
        <p><fmt:message key="errors.alreadySignedIn" bundle="${locale_prop}" /></p>
    </c:if>
    <c:if test="${sessionScope.user == null}">
        <form method="post">
            <label>Email</label>
            <input type="input" name="email">

            <label><fmt:message key="userInfo.password" bundle="${locale_prop}" /></label>
            <input type="password" name="password">

            <button type="submit" name="command" value="sign_in">
                <fmt:message key="buttons.signIn" bundle="${locale_prop}" />
            </button>
            <button>
                <a href="/welcome">
                    <fmt:message key="buttons.cancel" bundle="${locale_prop}" />
                </a>
            </button>
        </form>
    </c:if>
</body>
</html>
