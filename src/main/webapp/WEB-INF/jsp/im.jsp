<%@include file="template/Settings.jsp"%>

<%@include file="template/ImDefines.jsp"%>

<html>
<head>
    <title>${title}</title>
    <link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet">
</head>
<body>
    <h2>${about}</h2>

    <c:if test="${sessionScope.user != null}">
        <c:set var="user" value="${sessionScope.user}" />

        <p>
            Email :
            <c:out value="${user.email}"/>
        </p>
        <p>
            ${firstName} :
            <c:out value="${user.firstName}"/>
        </p>
        <p>
            ${lastName} :
            <c:out value="${user.lastName}"/>
        </p>
        <p>
            ${address} :
            <c:out value="${user.address}"/></p>
        <p>
            ${phone} :
            <c:out value="${user.phoneNumber}"/>
        </p>
        <p>
            ${gender} :
            <c:if test="${user.gender.equals('Male')}">
                ${male}
            </c:if>
            <c:if test="${user.gender.equals('Female')}">
                ${female}
            </c:if>
        </p>

        <form method="post">
            <button name="command" value="sign_out">
                ${logout}
            </button>
        </form>

    </c:if>
    <c:if test="${sessionScope.user == null}">
        ${errorNotFound}
    </c:if>
</body>
</html>
