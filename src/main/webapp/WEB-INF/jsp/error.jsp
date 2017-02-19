<%@include file="template/Settings.jsp"%>

<html>
<head>
    <title>Error</title>
</head>
<body>
    <h1>Error :</h1>
    <h2>
        <% String error = request.getParameter("error-message"); %>
        <% if (error == null) {
            error = (String) session.getAttribute("error-message");
        }%>
        <%=error%>
    </h2>
</body>
</html>
