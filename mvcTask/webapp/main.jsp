<%@ taglib uri="/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Welcome</title>
</head>
<body>
<h3>Welcome: ${user.name}</h3>
Your role is ${user.role}.
<br>
<c:if test="${user.role eq 'ADMIN'}">
	<h4>my respect!</h4>
</c:if>
<br>
<a href="/mvcTest/index.jsp">index page</a>
</body>
</html>
