<%@include file="template/Settings.jsp"%>
<%@include file="template/LoginDefines.jsp"%>

<html>
<head>
    <title>${title}</title>
</head>
<body>
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

</body>
</html>
