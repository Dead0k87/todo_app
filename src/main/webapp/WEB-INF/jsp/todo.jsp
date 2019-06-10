<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
    <%--THIS FORM UPDATES/DELETES DESCRIPTION, BECAUSE OF MODELATTRIBUTE="todo" AND <FORM:LABEL PATCH="desc"--%>
    <%--Эта страница для добавления/редактирования данных в TO DO задаче--%>
    <%--Add todo page for ${name}--%>
    <form:form method="post" modelAttribute="todo">
        <%--// hidden parameter of an ID--%>
        <form:hidden path="id"/>
        <%--// we are binding tot bean using Spring taglib --%>
        <fieldset class="form-group">
            <form:label path="desc">Description</form:label>
            <form:input path="desc" type="text" class="form-control" required="required"/>
            <form:errors path="desc" cssClass="text-warning"/>
        </fieldset>

        <%--//добавление нового поля для ввода текста (даты) на страницу--%>
        <fieldset class="form-group">
            <form:label path="targetDate">Target date</form:label>
            <form:input path="targetDate" type="text" class="form-control" required="required"/>
            <form:errors path="targetDate" cssClass="text-warning"/>
        </fieldset>
        <button type="submit" class="btn-success">Add</button>
    </form:form>
</div>

<%@ include file="common/footer.jspf" %>