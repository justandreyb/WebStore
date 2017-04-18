<%@include file="../defines/Settings.jsp"%>
<%@include file="../defines/LoginDefines.jsp"%>

<%@include file="template/head.jsp" %>

<html>
<head>
    <meta charset="utf-8">
    <title>${title}</title>
</head>
<body>
    <h2>${about}</h2>

    <c:if test="${sessionScope.user != null}">
        <p>${errorSigned}</p>
    </c:if>

    <c:if test="${sessionScope.user == null}">
        <form method="post">
            <p>
                <label>Email</label>
                <input type="input" name="email">
            </p>
            <p>
                <label>${password}</label>
                <input type="password" name="password">
            </p>
                <label>${firstName}</label>
                <input type="input" name="first_name">
            <p>
                <label>${lastName}</label>
                <input type="input" name="last_name">
            </p>
            <p>
                <label>${gender}</label>
                <select required name="gender">
                    <option>${genderUndefined}</option>
                    <option>${male}</option>
                    <option>${female}</option>
                </select>
            </p>
            <p>
                <label>${language}</label>
                <select required name="locale">
                    <option selected value="en">${english}</option>
                    <option value="ru">${russian}</option>
                </select>
            </p>
            <p>
                <label>${phone}</label>
                <input type="input" name="phone_number">
            </p>
            <p>
                <label>${address}</label>
                <input type="input" name="address">
            </p>
            <p>
                <button type="submit" name="command" value="sign_up">
                    ${signUp}
                </button>
                <button>
                    <a href="/welcome">
                        ${cancel}
                    </a>
                </button>
            </p>
        </form>
    </c:if>

</body>
</html>
