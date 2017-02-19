<%@include file="template/Settings.jsp"%>

<html>
<head>
    <title><fmt:message key="names.titleIm" bundle="${locale_prop}" /></title>
</head>
<body>
    <h2><fmt:message key="names.aboutIm" bundle="${locale_prop}" /></h2>


    <c:if test="${sessionScope.user != null}">
        <c:set var="user" value="${sessionScope.user}" />

        <p>
            Email :
            <c:out value="${user.email}"/>
        </p>
        <p>
            <fmt:message key="userInfo.firstName" bundle="${locale_prop}" /> :
            <c:out value="${user.firstName}"/>
        </p>
        <p>
            <fmt:message key="userInfo.lastName" bundle="${locale_prop}" /> :
            <c:out value="${user.lastName}"/>
        </p>
        <p>
            <fmt:message key="userInfo.address" bundle="${locale_prop}" /> :
            <c:out value="${user.address}"/></p>
        <p>
            <fmt:message key="userInfo.phone" bundle="${locale_prop}" /> :
            <c:out value="${user.phoneNumber}"/>
        </p>
        <p>
            <fmt:message key="userInfo.gender" bundle="${locale_prop}" /> :
            <c:if test="${user.gender.equals('Male')}">
                <fmt:message key="userInfo.genderMale" bundle="${locale_prop}" />
            </c:if>
            <c:if test="${user.gender.equals('Female')}">
                <fmt:message key="userInfo.genderFemale" bundle="${locale_prop}" />
            </c:if>
        </p>

        <form method="post">
            <button name="command" value="sign_out">
                <fmt:message key="buttons.logout" bundle="${locale_prop}" />
            </button>
        </form>

    </c:if>
    <c:if test="${sessionScope.user == null}">
        <fmt:message key="errors.userNotFound" bundle="${locale_prop}" />
    </c:if>
</body>
</html>
