<%@include file="template/Settings.jsp"%>
<%@include file="template/WelcomeDefines.jsp"%>

<html>
<head>
    <title>${title}</title>
</head>
<body>
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
</body>
</html>
