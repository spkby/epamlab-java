<%@ page import="by.gsu.epamlab.Constants" %>
<%@ taglib uri="/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Login</title></head>
<body>
<c:if test="${not empty errorMessage}">
<c:out value="${errorMessage}"/>
<hr>
</c:if>
<form name="loginForm" method="POST" action="<c:url value='/login'/>">
	Login:<br>
	<input type="text" name="login" value=""><br>
	Password:<br>
	<input type="password" name="password" value=""><br>
	<input type="submit" value="Enter">
</form>
<hr>
</body></html>
