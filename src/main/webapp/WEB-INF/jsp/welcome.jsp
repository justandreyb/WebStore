<%@include file="template/Settings.jsp"%>

<html>
<head>
    <title><fmt:message key="names.titleWelcome" bundle="${locale_prop}" /></title>
</head>
<body>
    <h2><fmt:message key="names.aboutWelcome" bundle="${locale_prop}" /></h2>
    <form method="post">
        <button title="Sign in">
            <a href="/login">
                <fmt:message key="buttons.signIn" bundle="${locale_prop}" />
            </a>
        </button>
        <button title="Sign up">
            <a href="/registration">
                <fmt:message key="buttons.signUp" bundle="${locale_prop}" />
            </a>
        </button>
    </form>
</body>
</html>
