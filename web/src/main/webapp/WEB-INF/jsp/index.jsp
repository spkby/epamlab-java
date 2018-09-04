<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>

<div class="jumbotron">
    <div class="container">
        <c:set var="login" value="Guest"/>
        <c:if test="${currAccount ne null}" var="isLogged">
            <c:set var="login" value="${currAccount.employee.name}"/>
        </c:if>
        <h2>Hello ${login}</h2>
        <c:if test="${!isLogged}">
            <a href="/login">Login</a>
        </c:if>
    </div>
</div>
<%@ include file="footer.jsp" %>