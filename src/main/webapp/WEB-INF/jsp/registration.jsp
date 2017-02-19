<%@include file="template/Settings.jsp"%>

<html>
<head>
    <meta charset="utf-8">
    <title><fmt:message key="names.titleSignUp" bundle="${locale_prop}" /></title>
</head>
<body>
    <h2><fmt:message key="names.aboutSignUp" bundle="${locale_prop}" /></h2>
    <c:if test="${sessionScope.user != null}">
        <p><fmt:message key="errors.alreadySignedIn" bundle="${locale_prop}" /></p>
    </c:if>
    <c:if test="${sessionScope.user == null}">
        <form method="post">
            <p>
                <label>Email</label>
                <input type="input" name="email">
            </p>
            <p>
                <label><fmt:message key="userInfo.password" bundle="${locale_prop}" /></label>
                <input type="password" name="password">
            </p>
                <label><fmt:message key="userInfo.firstName" bundle="${locale_prop}" /></label>
                <input type="input" name="first_name">
            <p>
                <label><fmt:message key="userInfo.lastName" bundle="${locale_prop}" /></label>
                <input type="input" name="last_name">
            </p>
            <p>
                <label><fmt:message key="userInfo.gender" bundle="${locale_prop}" /></label>
                <select required name="gender">
                    <option><fmt:message key="userInfo.genderUndefined" bundle="${locale_prop}" /></option>
                    <option><fmt:message key="userInfo.genderMale" bundle="${locale_prop}" /></option>
                    <option><fmt:message key="userInfo.genderFemale" bundle="${locale_prop}" /></option>
                </select>
            </p>
            <p>
                <label><fmt:message key="language.name" bundle="${locale_prop}" /></label>
                <select required name="locale">
                    <option selected value="en"><fmt:message key="language.english" bundle="${locale_prop}" /></option>
                    <option value="ru"><fmt:message key="language.russian" bundle="${locale_prop}" /></option>
                </select>
            </p>
            <p>
                <label><fmt:message key="userInfo.phone" bundle="${locale_prop}" /></label>
                <input type="input" name="phone_number">
            </p>
            <p>
                <label><fmt:message key="userInfo.address" bundle="${locale_prop}" /></label>
                <input type="input" name="address">
            </p>
            <p>
                <button type="submit" name="command" value="sign_up">
                    <fmt:message key="buttons.signUp" bundle="${locale_prop}" />
                </button>
                <button>
                    <a href="/welcome">
                        <fmt:message key="buttons.cancel" bundle="${locale_prop}" />
                    </a>
                </button>
            </p>
        </form>
    </c:if>
    <%--<%@page pageEncoding="UTF-8"%>--%>

</body>
</html>
