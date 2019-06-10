<html>
<head>
    <title>TODO for ${name}</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
          rel="stylesheet">
</head>

<%@ include file="common/header.jspf" %>
<%--navigation bar on top of the page--%>
<%@ include file="common/navigation.jspf" %>

<%--//bootsrap class--%>
<div class="container">
    <h1>Your ToDOs:</h1>
    <%--bootstrap class for table--%>
    <table class="table table-striped">
        <%--like header--%>
        <thead>
        <tr>
            <th>Description</th>
            <th>Target date</th>
            <th>Is it Done?</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${todos}" var="todo">
            <tr>
                <td> ${todo.desc} </td>
                <td><fmt:formatDate value="${todo.targetDate}" pattern="dd/MM/yyyy"/></td>
                <td> ${todo.done} </td>
                <td><a type="button" class="btn-success" href="/update-todo?id=${todo.id}">Update</a></td>
                <td><a type="button" class="btn-warning" href="/delete-todo?id=${todo.id}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <%--Here are the list of ${name} Todos:--%>
    <%--${todos};--%>

    <div>
        <%--Bootstrap class for button--%>
        <a class="button" href="/add-todo"> Add a TODO:</a>
    </div>
    <%--add java Script files--%>
</div>
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<%@ include file="common/footer.jspf" %>