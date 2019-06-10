<%--<html>--%>
<%--<head>--%>
<%--<title>Login page</title>--%>
<%--<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"--%>
<%--rel="stylesheet">--%>
<%--</head>--%>

<%--<body>--%>
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>


<div class="container">
    Welcome: ${name} !!! <a class="button" href="/list-todos">Click here</a> to manage your ToDo's:
</div>

<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<%@ include file="common/footer.jspf" %>