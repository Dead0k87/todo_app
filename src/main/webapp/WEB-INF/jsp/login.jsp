<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
    <font color="red"> ${errorMessage}</font>
    <form:form method="post">
        Name: <input type="text" name="name">
        Password: <input type="password" name="password">
        <button type="submit" class="btn-success">Submit</button>
    </form:form>
    <%--First JSP page. Welcome, ${name} !!!--%>

</div>
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<%@ include file="common/footer.jspf" %>